package ch.andres.demo.webapp.controller;

import ch.andres.demo.webapp.controller.dto.OrderDto;
import ch.andres.demo.webapp.domain.OrderService;
import ch.andres.demo.webapp.misc.ApplicationException;
import ch.andres.demo.webapp.misc.JmsConfig;
import ch.andres.demo.webapp.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	private final JmsTemplate jmsTemplate;

	// DEMO: get function due no postman installed
	// use right method such as POST, no verbs in URL
	@GetMapping("create")
	public OrderDto createOrder() throws ApplicationException {

		OrderDto dto = OrderDto.builder()
				.customerId("123")
				.amount(BigDecimal.valueOf(123.123))
				.build();

		return createOrder(dto);
	}

	@GetMapping("create-missing")
	public OrderDto forceErrorOnCreate() throws ApplicationException {

		OrderDto dto = OrderDto.builder()
				// no customerId
				.amount(BigDecimal.valueOf(55.90))
				.build();

		return createOrder(dto);
	}

	@PostMapping()
	public OrderDto createOrder(@RequestBody OrderDto dto) throws ApplicationException {

		log.info("Receiving dto...");

		if (dto.getCustomerId() == null || dto.getAmount() == null) {
			throw new ApplicationException("Missing required fields: customerId and amount are required.");
		}

		// convert to entity
		Order order = Order.builder()
				.customerId(dto.getCustomerId())
				.amount(dto.getAmount())
				.build();

		// write to DB
		// TODO: stores 123.12 but returns 123.123 use validation before write and/or reload object from DB
		order = orderService.createOrder(order);

		// write to queue
		Map<String, Object> message = new HashMap<>();
		message.put("id", order.getId());
		message.put("customerId", order.getCustomerId());
		jmsTemplate.convertAndSend(JmsConfig.QUEUE_NAME, message);

		// convert to DTO
		return OrderDto.builder()
				.id(order.getId())
				.customerId(order.getCustomerId())
				.amount(order.getAmount())
				.build();
	}

}

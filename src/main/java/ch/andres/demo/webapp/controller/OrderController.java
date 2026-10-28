package ch.andres.demo.webapp.controller;

import ch.andres.demo.webapp.controller.dto.OrderDto;
import ch.andres.demo.webapp.domain.OrderService;
import ch.andres.demo.webapp.misc.ApplicationException;
import ch.andres.demo.webapp.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	// DEMO: get function due no postman installed
	// use right method such as POST, no verbs in URL
	@GetMapping("create")
	public OrderDto createOrder() throws ApplicationException {

		OrderDto dto = OrderDto.builder()
				.customerId(123L)
				.amount(BigDecimal.valueOf(55.90))
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

		// ignore ID
		Order order = Order.builder()
				.customerId(dto.getCustomerId())
				.amount(dto.getAmount())
				.build();

		order = orderService.createOrder(order);

		// use e.g. Mapstruct
		dto.setId(order.getId());

		return dto;
	}

}

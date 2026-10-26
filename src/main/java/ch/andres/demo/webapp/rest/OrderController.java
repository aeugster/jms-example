package ch.andres.demo.webapp.rest;

import ch.andres.demo.webapp.domain.OrderService;
import ch.andres.demo.webapp.dto.OrderDto;
import ch.andres.demo.webapp.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	// DEMO: get function due no postman installed
	// use right method such as POST, no verbs in URL
	@GetMapping("create")
	public OrderDto createOrder() {

		OrderDto dto = OrderDto.builder()
				.customerId(123)
				.amount(BigDecimal.valueOf(55.90))
				.build();
		createOrder(dto);

		return dto;
	}

	@PostMapping()
	public Order createOrder(@RequestBody OrderDto dto) {

		log.info("Receiving dto...");

		Order order = Order.builder()
				.customerId(dto.getCustomerId())
				.amount(dto.getAmount())
				.build();

		return orderService.createOrder(order);
	}

}

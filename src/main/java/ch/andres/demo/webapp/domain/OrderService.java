package ch.andres.demo.webapp.domain;

import ch.andres.demo.webapp.model.Order;
import ch.andres.demo.webapp.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	public Order createOrder(Order order) {
		var createdOrder = orderRepository.save(order);
		log.info("Order {} created with status {}",
				createdOrder.getId(), createdOrder.getStatus());
		return createdOrder;
	}
}

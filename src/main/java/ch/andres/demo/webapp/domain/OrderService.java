package ch.andres.demo.webapp.domain;

import ch.andres.demo.webapp.model.Order;
import ch.andres.demo.webapp.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}
}

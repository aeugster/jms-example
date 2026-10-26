package ch.andres.demo.webapp.domain;

import ch.andres.demo.webapp.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {
	public Order createOrder(Order order) {
		// TODO: implement order creation logic
		return null;
	}
}

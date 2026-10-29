package ch.andres.demo.webapp.domain;

import ch.andres.demo.webapp.misc.JmsConfig;
import ch.andres.demo.webapp.model.Order;
import ch.andres.demo.webapp.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

	private final JmsTemplate jmsTemplate;

	private final OrderRepository orderRepository;

	public Order createOrder(Order order) {
		order = orderRepository.save(order);

		// TODO: use a OrderMessage POJO instead?
		// TODO: handle BigDecimal serialization issue
		Map<String, Object> message = new HashMap<>();
		message.put("id", order.getId());
		message.put("customerId", order.getCustomerId());
		message.put("amount", order.getAmount().toBigInteger().floatValue());

		jmsTemplate.convertAndSend(JmsConfig.QUEUE_NAME, message);

		return order;
	}
}

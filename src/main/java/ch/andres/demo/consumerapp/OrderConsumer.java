package ch.andres.demo.consumerapp;

import ch.andres.demo.webapp.misc.JmsConfig;
import ch.andres.demo.webapp.model.Order;
import ch.andres.demo.webapp.model.OrderStatus;
import ch.andres.demo.webapp.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConsumer {

	private final OrderRepository orderRepository;

	@JmsListener(destination = JmsConfig.QUEUE_NAME)
	public void receiveMessage(Map<String, ?> order) throws InterruptedException {
		log.info("Consuming message: {}", order);

		Thread.sleep(5000);
		log.info("Processing order...");

		markOrderAsProcessed((long) order.get("id"));
	}

	private void markOrderAsProcessed(long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow();

		order.setStatus(OrderStatus.PROCESSED);
		orderRepository.save(order);

		log.info("Order {} marked as PROCESSED", orderId);
	}
}
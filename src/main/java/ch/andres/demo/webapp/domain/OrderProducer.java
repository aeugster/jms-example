package ch.andres.demo.webapp.domain;


import ch.andres.demo.webapp.misc.JmsConfig;
import ch.andres.demo.webapp.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderProducer {
	private final JmsTemplate jmsTemplate;

	public void sendMessage(Order message) {
		jmsTemplate.convertAndSend(JmsConfig.QUEUE_NAME, message);
		log.info("Message produced: {}", message);
	}
}
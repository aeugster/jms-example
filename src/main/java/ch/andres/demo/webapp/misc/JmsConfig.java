package ch.andres.demo.webapp.misc;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfig {
	public static final String QUEUE_NAME = "order.created";

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("vm://localhost?broker.persistent=false");
		return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory) {
		return new JmsTemplate(connectionFactory);
	}
}
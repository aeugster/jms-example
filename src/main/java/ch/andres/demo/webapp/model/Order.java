package ch.andres.demo.webapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@Builder.Default
	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.CREATED;

	/**
	 * values like "12345"
	 */
	@Column(nullable = false)
	String customerId;

	@Column(nullable = false, precision = 19, scale = 2)
	BigDecimal amount;
}

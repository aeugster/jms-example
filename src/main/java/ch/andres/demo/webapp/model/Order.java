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

	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.CREATED;

	@Column(nullable = false)
	Long customerId;

	@Column(nullable = false)
	BigDecimal amount;
}

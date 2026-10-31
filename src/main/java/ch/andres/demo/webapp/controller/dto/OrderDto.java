package ch.andres.demo.webapp.controller.dto;

import ch.andres.demo.webapp.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
public class OrderDto {

	Long id;

	String customerId;
	BigDecimal amount;

	OrderStatus status;
}

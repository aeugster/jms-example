package ch.andres.demo.webapp.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class OrderDto {

	Long id;

	Long customerId;
	BigDecimal amount;

}

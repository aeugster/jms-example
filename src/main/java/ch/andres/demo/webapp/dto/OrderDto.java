package ch.andres.demo.webapp.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class OrderDto {

	int customerId;
	BigDecimal amount;

}

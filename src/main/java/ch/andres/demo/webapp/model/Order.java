package ch.andres.demo.webapp.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Order {

	int customerId;
	BigDecimal amount;

}

package ch.andres.demo.webapp.repositories;

import ch.andres.demo.webapp.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}

package eu.kaluzinski.jdbc;

import eu.kaluzinski.jdbc.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

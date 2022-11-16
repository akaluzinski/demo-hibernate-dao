package eu.kaluzinski.jdbc.repositories;

import eu.kaluzinski.jdbc.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}

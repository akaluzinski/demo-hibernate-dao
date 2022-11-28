package eu.kaluzinski.jdbc;

import eu.kaluzinski.jdbc.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}

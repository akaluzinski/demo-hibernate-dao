package eu.kaluzinski.jdbc.repositories;

import eu.kaluzinski.jdbc.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

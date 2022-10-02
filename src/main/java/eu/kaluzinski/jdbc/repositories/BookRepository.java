package eu.kaluzinski.jdbc.repositories;

import eu.kaluzinski.jdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

package eu.kaluzinski.jdbc.repositories;

import eu.kaluzinski.jdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitle(String title);

    Optional<Book> findBooksByIsbn(String isbn);

    Stream<Book> findAllByTitleNotNull();

}

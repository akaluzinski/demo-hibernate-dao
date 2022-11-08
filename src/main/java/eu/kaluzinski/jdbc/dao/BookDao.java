package eu.kaluzinski.jdbc.dao;

import eu.kaluzinski.jdbc.domain.Author;
import eu.kaluzinski.jdbc.domain.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookDao {

    List<Book> findAll();

    List<Book> findAll(Pageable pageable);

    List<Book> findAllSortByTitle(Pageable pageable);

    Book getById(Long id);

    Book findByISBN(String isbn);

    Book findBookByTitle(String title);


    Book saveNewBook(Book book);

    Book updateBook(Book book);

    Book setAuthor(Author author);

    void deleteBookById(Long id);

}

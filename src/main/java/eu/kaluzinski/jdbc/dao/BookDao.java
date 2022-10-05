package eu.kaluzinski.jdbc.dao;

import eu.kaluzinski.jdbc.domain.Author;
import eu.kaluzinski.jdbc.domain.Book;

public interface BookDao {
    Book getById(Long id);

    Book findByISBN(String isbn);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    Book setAuthor(Author author);

    void deleteBookById(Long id);

}

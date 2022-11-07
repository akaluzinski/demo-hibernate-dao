package eu.kaluzinski.jdbc.dao;

import eu.kaluzinski.jdbc.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAuthorsByLastNameLike(String lastName);

    Author getById(Long id);

    List<Author> findAll();

    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);

    Author findAuthorByNameNative(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}

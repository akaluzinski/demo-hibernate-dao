package eu.kaluzinski.jdbc;

import eu.kaluzinski.jdbc.dao.AuthorDao;
import eu.kaluzinski.jdbc.dao.AuthorDaoImpl;
import eu.kaluzinski.jdbc.dao.BookDao;
import eu.kaluzinski.jdbc.dao.BookDaoImpl;
import eu.kaluzinski.jdbc.domain.Author;
import eu.kaluzinski.jdbc.domain.Book;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@Import({AuthorDaoImpl.class, BookDaoImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoIntegrationTest {
    @Autowired
    AuthorDao authorDao;

    @Autowired
    BookDao bookDao;

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        assertThrows(EntityNotFoundException.class, () -> {
            Book deleted = bookDao.getById(saved.getId());
        });
    }

    @Test
    void updateBookTest() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");

        Author author = new Author();
        author.setId(3L);

        book.setAuthorId(author.getId());
        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("New Book");
        bookDao.updateBook(saved);

        Book fetched = bookDao.getById(saved.getId());

        assertThat(fetched.getTitle()).isEqualTo("New Book");
    }

    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");

        Author author = new Author();
        author.setId(3L);

        book.setAuthorId(author.getId());
        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
    }

    @Test
    void testGetBookByName() {
        Book book = bookDao.findBookByTitle("Clean Code");

        assertThat(book).isNotNull();
    }

    @Test
    void testGetBookByISBN() {
        Book book = bookDao.findByISBN("978-1617292545");

        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("Spring Boot in Action, 1st Edition");
    }

    @Test
    void testFindAllBooks() {
        List<Book> books = bookDao.findAll();
        assertThat(books).isNotNull();
        assertThat(books.size()).isPositive();
    }

    @Test
    void testGetBook() {
        Book book = bookDao.getById(3L);

        assertThat(book.getId()).isNotNull();
    }

    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("xd");
        author.setLastName("xd1");

        authorDao.saveNewAuthor(author);
        Author saved = authorDao.findAuthorByName(author.getFirstName(), author.getLastName());
        assertThat(saved.getId()).isNotNull();
        authorDao.deleteAuthorById(saved.getId());

        assertThrows(EntityNotFoundException.class, () -> {
            Author deleted = authorDao.findAuthorByName(author.getFirstName(), author.getLastName());
        });
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");

        Author saved = authorDao.saveNewAuthor(author);

        saved.setLastName("Thompson");
        Author updated = authorDao.updateAuthor(saved);

        assertThat(updated.getLastName()).isEqualTo("Thompson");
    }

    @Test
    void testSaveAuthor() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Thompson");
        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void testFindAllAuthors() {
        List<Author> authors = authorDao.findAll();
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isPositive();
    }

    @Test
    void testGetAuthorByName() {
        Author author = authorDao.findAuthorByName("Craig", "Walls");

        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthorByNameNotFoundThrowsException() {
        assertThrows(EntityNotFoundException.class, () -> {
            authorDao.findAuthorByName("Uniqye", "Unique");
        });
    }

    @Test
    void testGetAuthorByNameNative() {
        Author author = authorDao.findAuthorByNameNative("Craig", "Walls");

        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthor() {

        Author author = authorDao.getById(1L);

        assertThat(author).isNotNull();

    }

    @Test
    void testGetAuthorsByLastNameLike() {
        List<Author> authors = authorDao.getAuthorsByLastNameLike("Wall");

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isPositive();
    }
}

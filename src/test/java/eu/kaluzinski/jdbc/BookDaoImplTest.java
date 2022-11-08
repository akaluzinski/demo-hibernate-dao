package eu.kaluzinski.jdbc;

import eu.kaluzinski.jdbc.dao.BookDao;
import eu.kaluzinski.jdbc.dao.BookDaoImpl;
import eu.kaluzinski.jdbc.domain.Book;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(BookDaoImpl.class)
class BookDaoImplTest {

    @Autowired
    BookDao bookDao;

    @Test
    void shouldFindFirstPageableBooksPage() {
        List<Book> books = bookDao.findAll(PageRequest.of(0, 10));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void shouldFindSecondPageableBooksPage() {
        List<Book> books = bookDao.findAll(PageRequest.of(1, 10));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void shouldFindSecondPageableBooksPageWithCustomPageSize() {
        List<Book> books = bookDao.findAll(PageRequest.of(10, 10));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    void shouldFindAllBooks() {
        List<Book> books = bookDao.findAll();

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(5);
    }

    @Test
    void shouldGetBookById() {
        Book book = bookDao.getById(3L);

        assertThat(book.getId()).isNotNull();
    }

    @Test
    void shouldFindBookByTitle() {
        Book book = bookDao.findBookByTitle("Clean Code");

        assertThat(book).isNotNull();
    }

    @Test
    void shouldSaveBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(1L);

        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
    }

    @Test
    void shouldUpdateBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(1L);
        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("New Book");
        bookDao.updateBook(saved);

        Book fetched = bookDao.getById(saved.getId());

        assertThat(fetched.getTitle()).isEqualTo("New Book");
    }

    @Test
    void shouldDeleteBookById() {

        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        assertThrows(EntityNotFoundException.class, () -> {
            bookDao.getById(saved.getId());
        });
    }
}

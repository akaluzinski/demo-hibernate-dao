package eu.kaluzinski.jdbc.dao;

import eu.kaluzinski.jdbc.domain.Author;
import eu.kaluzinski.jdbc.domain.Book;
import eu.kaluzinski.jdbc.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao {


    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).getContent();
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book findByISBN(String isbn) {
        return bookRepository.findBooksByIsbn(isbn)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book saveNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        Book foundBook = bookRepository.getReferenceById(book.getId());
        foundBook.setAuthorId(book.getAuthorId());
        foundBook.setTitle(book.getTitle());
        foundBook.setPublisher(book.getPublisher());
        foundBook.setAuthorId(book.getAuthorId());
        return bookRepository.save(foundBook);
    }

    @Override
    public Book setAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}

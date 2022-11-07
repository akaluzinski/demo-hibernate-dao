package eu.kaluzinski.jdbc.dao;

import eu.kaluzinski.jdbc.domain.Author;
import eu.kaluzinski.jdbc.domain.Book;
import eu.kaluzinski.jdbc.repositories.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao {


    private final EntityManagerFactory emf;
    private final BookRepository bookRepository;

    public BookDaoImpl(EntityManagerFactory emf, BookRepository bookRepository) {
        this.emf = emf;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book findByISBN(String isbn) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b where b.isbn = :isbn", Book.class);
            query.setParameter("isbn", isbn);

            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public Book findBookByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Book> query = em.createNamedQuery("find_by_title", Book.class);
            query.setParameter("book_title", title);
            return query.getSingleResult();
        } finally {
            em.close();
        }
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

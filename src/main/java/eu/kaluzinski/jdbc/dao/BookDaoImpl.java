package eu.kaluzinski.jdbc.dao;

import eu.kaluzinski.jdbc.domain.Author;
import eu.kaluzinski.jdbc.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {


    private final EntityManagerFactory emf;

    public BookDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Book getById(Long id) {
        EntityManager em = emf.createEntityManager();
        Book book = em.find(Book.class, id);
        em.close();
        return book;
    }

    @Override
    public Book findBookByTitle(String title) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.title = :book_title", Book.class);
        query.setParameter("book_title", title);
        return query.getSingleResult();
    }

    @Override
    public Book saveNewBook(Book book) {
        return null;
    }

    @Override
    public Book setAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {

    }
}

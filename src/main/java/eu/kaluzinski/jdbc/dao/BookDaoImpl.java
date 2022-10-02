package eu.kaluzinski.jdbc.dao;

import eu.kaluzinski.jdbc.domain.Author;
import eu.kaluzinski.jdbc.domain.Book;
import jakarta.persistence.EntityManager;
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
        Book book = em.find(Book.class, id);;
        em.close();
        return book;
    }

    @Override
    public Book findBookByTitle(String title) {
        return null;
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

package eu.kaluzinski.jdbc.dao;

import eu.kaluzinski.jdbc.domain.Author;
import eu.kaluzinski.jdbc.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao {


    private final EntityManagerFactory emf;

    public BookDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Book> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("find_all", Book.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Book getById(Long id) {
        EntityManager em = emf.createEntityManager();
        Book book = em.find(Book.class, id);
        em.close();
        return book;
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
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(book);
        em.flush();
        et.commit();
        em.close();
        return book;
    }

    public Book updateBook(Book book) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(book);
        em.flush();
        em.clear();
        Book updatedBook = em.find(Book.class, book.getId());
        et.commit();
        em.close();
        return updatedBook;
    }

    @Override
    public Book setAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Book book = em.find(Book.class, id);
        em.remove(book);
        et.commit();
    }
}

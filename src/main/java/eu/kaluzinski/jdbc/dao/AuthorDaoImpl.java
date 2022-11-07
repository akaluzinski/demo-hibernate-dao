package eu.kaluzinski.jdbc.dao;

import eu.kaluzinski.jdbc.domain.Author;
import eu.kaluzinski.jdbc.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAuthorsByLastNameLike(String lastName) {
        return authorRepository.findAllAuthorsByLastNameLikeQuery(lastName);
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.getReferenceById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Author findAuthorByNameNative(String firstName, String lastName) {
        return authorRepository.findAuthorByNameNative(firstName, lastName);
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
        Author foundAuthor = getById(author.getId());
        foundAuthor.setFirstName(author.getFirstName());
        foundAuthor.setLastName(author.getLastName());
        return authorRepository.save(foundAuthor);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

}

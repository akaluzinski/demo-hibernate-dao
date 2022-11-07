package eu.kaluzinski.jdbc.repositories;


import eu.kaluzinski.jdbc.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {


    @Query("SELECT a FROM Author a where a.lastName LIKE ?1%")
    List<Author> findAllAuthorsByLastNameLikeQuery(String authorLastName);

    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);

}

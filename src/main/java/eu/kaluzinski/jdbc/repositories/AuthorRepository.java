package eu.kaluzinski.jdbc.repositories;


import eu.kaluzinski.jdbc.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {


    @Query("SELECT a FROM Author a where a.lastName LIKE :lastName%")
    List<Author> findAllAuthorsByLastNameLikeQuery(@Param("lastName") String lastName);

    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);

}

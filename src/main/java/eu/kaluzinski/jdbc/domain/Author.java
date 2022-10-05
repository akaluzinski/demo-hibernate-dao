package eu.kaluzinski.jdbc.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import lombok.Getter;
import lombok.Setter;

@NamedQueries({
        @NamedQuery(name = "author_find_all", query = "FROM Author"),
        @NamedQuery(name = "author_find_by_name", query = "FROM Author a WHERE a.firstName = :first_name AND a.lastName = :last_name")
})
@Entity
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
}

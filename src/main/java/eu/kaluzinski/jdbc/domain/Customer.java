package eu.kaluzinski.jdbc.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 50)
    private String customerName;

    @Valid
    @Embedded
    private Address address;

    @Length(max = 20)
    private String phone;

    @Length(max = 30)
    private String email;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "customer")
    @NotNull
    private Set<OrderHeader> orderHeaders = new LinkedHashSet<>();

}

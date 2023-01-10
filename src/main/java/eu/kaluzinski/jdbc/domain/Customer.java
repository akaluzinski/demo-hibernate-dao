package eu.kaluzinski.jdbc.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    private String customerName;

    @Valid
    @Embedded
    private Address address;

    @Size(max = 20)
    private String phone;

    @Email
    @Size(max = 30)
    private String email;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "customer")
    @NotNull
    private Set<OrderHeader> orderHeaders = new LinkedHashSet<>();

}

package eu.kaluzinski.jdbc.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @Embedded
    private Address address;
    private String phone;
    private String email;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "customer")
    private Set<OrderHeader> orderHeaders = new LinkedHashSet<>();


}

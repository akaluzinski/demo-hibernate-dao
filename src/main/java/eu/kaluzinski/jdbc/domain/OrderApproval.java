package eu.kaluzinski.jdbc.domain;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class OrderApproval extends BaseEntity {

    private String approvedBy;
}

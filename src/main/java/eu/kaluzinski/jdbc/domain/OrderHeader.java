package eu.kaluzinski.jdbc.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
public class OrderHeader extends BaseEntity {


    private String customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderHeader that = (OrderHeader) o;

        return Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }
}

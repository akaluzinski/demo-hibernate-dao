package eu.kaluzinski.jdbc.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
public class OrderLine extends BaseEntity {

    private Integer quantityOrder;
    @ManyToOne
    private OrderHeader orderHeader;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderLine orderLine = (OrderLine) o;

        if (!Objects.equals(quantityOrder, orderLine.quantityOrder))
            return false;
        return Objects.equals(orderHeader, orderLine.orderHeader);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (quantityOrder != null ? quantityOrder.hashCode() : 0);
        result = 31 * result + (orderHeader != null ? orderHeader.hashCode() : 0);
        return result;
    }
}

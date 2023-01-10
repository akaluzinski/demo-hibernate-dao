package eu.kaluzinski.jdbc.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class Address {
    @Length(max = 30)
    private String address;
    @Length(max = 20)
    private String city;
    @Length(max = 20)
    private String state;

    @Length(max = 20)
    private String zipCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address1 = (Address) o;

        if (!Objects.equals(address, address1.address)) return false;
        if (!Objects.equals(city, address1.city)) return false;
        if (!Objects.equals(state, address1.state)) return false;
        return Objects.equals(zipCode, address1.zipCode);
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        return result;
    }
}

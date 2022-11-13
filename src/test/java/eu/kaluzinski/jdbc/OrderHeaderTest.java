package eu.kaluzinski.jdbc;

import eu.kaluzinski.jdbc.domain.OrderHeader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderHeaderTest {

    @Test
    void testEquals() {
        OrderHeader oh1 = new OrderHeader();
        oh1.setId(1L);

        OrderHeader oh2 = new OrderHeader();
        oh2.setId(2L);

        assertThat(oh1).isNotEqualTo(oh2);
    }
}

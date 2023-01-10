package eu.kaluzinski.jdbc;

import eu.kaluzinski.jdbc.domain.Address;
import eu.kaluzinski.jdbc.domain.Customer;
import eu.kaluzinski.jdbc.domain.OrderApproval;
import eu.kaluzinski.jdbc.domain.OrderHeader;
import eu.kaluzinski.jdbc.domain.OrderLine;
import eu.kaluzinski.jdbc.domain.Product;
import eu.kaluzinski.jdbc.domain.ProductStatus;
import eu.kaluzinski.jdbc.repositories.OrderHeaderRepository;
import eu.kaluzinski.jdbc.repositories.ProductRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderHeaderRepositoryTest {

    public static final String TOO_LONG_VALUE = "SomenameSomenameSomenameSomenameSomenameSomenameSomenameSomenameSomenameSomenameSomename";
    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderApprovalRepository orderApprovalRepository;

    Product product;

    @BeforeEach
    void setUp() {
        Product product1 = new Product();
        product1.setDescription("Some description1");
        product1.setProductStatus(ProductStatus.NEW);
        product = productRepository.saveAndFlush(product1);

        OrderHeader orderHeader = new OrderHeader();
        orderHeaderRepository.saveAndFlush(orderHeader);
    }

    @Test
    void shouldSaveOrderWithLine() {
        OrderHeader orderHeader = new OrderHeader();

        Customer customer = new Customer();
        customer.setCustomerName("Somename");
        Customer savedCustomer = customerRepository.save(customer);
        orderHeader.setCustomer(savedCustomer);

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrder(2);
        orderLine.setProduct(product);
        orderHeader.addOrderLine(orderLine);

        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy("Me!");
        OrderApproval savedOrderApproval = orderApprovalRepository.save(orderApproval);
        orderHeader.setOrderApproval(savedOrderApproval);

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertNotNull(savedOrder.getOrderLines());
        assertEquals(savedOrder.getOrderLines().size(), 1);
    }

    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
        Customer customer = new Customer();
        customer.setCustomerName("Somename");
        Customer savedCustomer = customerRepository.save(customer);
        orderHeader.setCustomer(savedCustomer);

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());

        OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());
        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.getId());
        assertNotNull(fetchedOrder.getCreatedDate());
        assertNotNull(fetchedOrder.getUpdatedDate());

    }

    @Test
    void shouldValidateMaxCustomerName() {
        Customer customer = new Customer();

        assertThrows(ConstraintViolationException.class, () -> {
            customer.setCustomerName(TOO_LONG_VALUE);
            customerRepository.save(customer);
        });
    }

    @Test
    void shouldValidateCustomerPhoneNumber() {
        Customer customer = new Customer();

        assertThrows(ConstraintViolationException.class, () -> {
            customer.setPhone(TOO_LONG_VALUE);
            customerRepository.save(customer);
        });
    }

    @Test
    void shouldValidateCustomerEmailFormat() {
        Customer customer = new Customer();

        assertThrows(ConstraintViolationException.class, () -> {
            customer.setEmail("xD@");
            customerRepository.save(customer);
        });
    }

    @Test
    void shouldValidateCustomerEmailFormatSize() {
        Customer customer = new Customer();

        assertThrows(ConstraintViolationException.class, () -> {
            customer.setEmail(TOO_LONG_VALUE);
            customerRepository.save(customer);
        });
    }

    @Test
    void shouldValidateCustomerAddress() {
        Customer customer = new Customer();
        Address address = new Address();

        assertThrows(ConstraintViolationException.class, () -> {
            address.setCity(TOO_LONG_VALUE);
            customer.setAddress(address);
            customerRepository.save(customer);
        });
    }

    @Test
    void testUpdatingAddress() {
        List<OrderHeader> orderHeaderList = orderHeaderRepository.findAll();
        assertNotNull(orderHeaderList);
        assertTrue(orderHeaderList.size() > 0);

        OrderHeader orderHeader = orderHeaderList.stream().findAny().get();

        Address billToAddress = new Address();
        orderHeader.setBillToAddress(billToAddress);
        orderHeaderRepository.saveAndFlush(orderHeader);

        OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(orderHeader.getId());
        assertNotNull(fetchedOrder);
        assertEquals(fetchedOrder.getBillToAddress(), billToAddress);
    }

}

package eu.kaluzinski.jdbc.bootstrap;

import eu.kaluzinski.jdbc.CustomerRepository;
import eu.kaluzinski.jdbc.domain.Customer;
import eu.kaluzinski.jdbc.domain.OrderHeader;
import eu.kaluzinski.jdbc.domain.OrderStatus;
import eu.kaluzinski.jdbc.repositories.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
        Customer customer = new Customer();
        customer.setCustomerName("Joe Doe");
        Customer savedCustomer = customerRepository.save(customer);

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(savedCustomer);
        orderHeader.setOrderStatus(OrderStatus.NEW);
        orderHeaderRepository.saveAndFlush(orderHeader);
    }
}

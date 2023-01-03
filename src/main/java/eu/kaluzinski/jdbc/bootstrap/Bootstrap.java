package eu.kaluzinski.jdbc.bootstrap;

import eu.kaluzinski.jdbc.repositories.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Override
    public void run(String... args) {

    }
}

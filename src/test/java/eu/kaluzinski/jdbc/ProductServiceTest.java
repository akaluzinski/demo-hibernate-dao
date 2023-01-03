package eu.kaluzinski.jdbc;

import eu.kaluzinski.jdbc.domain.Product;
import eu.kaluzinski.jdbc.domain.ProductStatus;
import eu.kaluzinski.jdbc.repositories.ProductRepository;
import eu.kaluzinski.jdbc.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = {ProductService.class})
public class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    void shouldAddAndUpdateProductQuantity() {
        Product product = new Product();
        product.setDescription("Some description");
        product.setProductStatus(ProductStatus.IN_STOCK);

        Product savedProduct = productService.saveProduct(product);

        Product savedProduct2 = productService.updateQuantityOnHand(savedProduct.getId(), 67);
        assertEquals(savedProduct2.getQuantityOnHand(), 67);
    }
}

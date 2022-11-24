package eu.kaluzinski.jdbc;

import eu.kaluzinski.jdbc.domain.Product;
import eu.kaluzinski.jdbc.domain.ProductStatus;
import eu.kaluzinski.jdbc.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void shouldGetCategory() {
        Product product = productRepository.findByDescription("PRODUCT1");
        assertNotNull(product);
        assertNotNull(product.getCategories());
    }

    @Test
    void testProduct() {
        Product product = new Product();
        product.setDescription("Some description");
        product.setProductStatus(ProductStatus.IN_STOCK);

        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getId());

        Product fetchedProduct = productRepository.getReferenceById(product.getId());
        assertNotNull(fetchedProduct);
        assertNotNull(fetchedProduct.getId());
        assertNotNull(fetchedProduct.getCreatedDate());
        assertNotNull(fetchedProduct.getUpdatedDate());

    }
}

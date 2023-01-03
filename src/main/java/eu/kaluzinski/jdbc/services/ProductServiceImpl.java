package eu.kaluzinski.jdbc.services;

import eu.kaluzinski.jdbc.domain.Product;
import eu.kaluzinski.jdbc.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product updateQuantityOnHand(Long id, Integer quantityOnHand) {
        Product product = productRepository.getReferenceById(id);
        product.setQuantityOnHand(quantityOnHand);
        return productRepository.saveAndFlush(product);
    }
}

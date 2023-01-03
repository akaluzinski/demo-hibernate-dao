package eu.kaluzinski.jdbc.services;

import eu.kaluzinski.jdbc.domain.Product;

public interface ProductService {

    Product saveProduct(Product product);

    Product updateQuantityOnHand(Long id, Integer quantityOnHand);
}

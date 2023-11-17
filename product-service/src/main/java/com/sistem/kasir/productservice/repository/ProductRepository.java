package com.sistem.kasir.productservice.repository;

import com.sistem.kasir.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}

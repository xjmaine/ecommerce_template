package com.web.ecom_template.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import com.web.ecom_template.entity.Product;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long> {
}

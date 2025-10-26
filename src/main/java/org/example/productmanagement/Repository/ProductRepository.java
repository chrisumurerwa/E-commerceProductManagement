package org.example.productmanagement.Repository;

import org.example.productmanagement.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findProductsByPriceGreaterThan(double price);

    @Query(value = "SELECT * FROM product WHERE category_id = :categoryId", nativeQuery = true)
    List<Product> findProductsByCategory(Long categoryId);
}

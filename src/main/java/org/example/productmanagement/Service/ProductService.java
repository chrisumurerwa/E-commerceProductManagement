package org.example.productmanagement.Service;

import org.example.productmanagement.Dto.ProductDto;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    void deleteProduct(Long id);
}

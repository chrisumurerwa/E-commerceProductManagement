package org.example.productmanagement.Service;

import org.example.productmanagement.Dto.ProductDto;
import org.example.productmanagement.Model.Product;
import org.example.productmanagement.Model.Category;
import org.example.productmanagement.Repository.ProductRepository;
import org.example.productmanagement.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);

        productRepository.save(product);

        productDto.setId(product.getId());
        return productDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(p -> {
            ProductDto dto = new ProductDto();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setPrice(p.getPrice());
            dto.setDescription(p.getDescription());
            dto.setCategoryId(p.getCategory().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductDto dto = new ProductDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setDescription(p.getDescription());
        dto.setCategoryId(p.getCategory().getId());
        return dto;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

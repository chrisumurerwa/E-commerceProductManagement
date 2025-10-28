package org.example.productmanagement.Service;

import org.example.productmanagement.Dto.ProductDto;
import org.example.productmanagement.GlobalExceptionHandling.resourcesExistsException;
import org.example.productmanagement.Model.Category;
import org.example.productmanagement.Model.Product;
import org.example.productmanagement.Repository.CategoryRepository;
import org.example.productmanagement.Repository.ProductRepository;
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

    // Helper: convert entity -> dto
    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
        }
        return dto;
    }

    // Helper: convert dto -> entity
    private Product mapToEntity(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }
        return product;
    }

    @Override
    public ProductDto createProduct(ProductDto dto) {
        if (productRepository.existsByName(dto.getName())) {
            throw new resourcesExistsException("Product with name " + dto.getName() + " already exists");

        }
        Product product = mapToEntity(dto);
        Product saved = productRepository.save(product); //  this inserts into the product table
        return mapToDto(saved);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

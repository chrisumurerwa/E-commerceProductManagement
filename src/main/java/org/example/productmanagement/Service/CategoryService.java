package org.example.productmanagement.Service;

import org.example.productmanagement.Dto.CategoryDto;

import java.util.List;

public abstract class CategoryService {
    public abstract CategoryDto createCategory(CategoryDto categoryDto);

    public abstract List<CategoryDto> getAllCategories();

    public abstract CategoryDto getCategoryById(Long id);

    public abstract void deleteCategory(Long id);
}

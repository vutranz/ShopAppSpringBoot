package com.project.shopapp.Service;

import com.project.shopapp.DTO.CategoryDTO;
import com.project.shopapp.Models.Category;

import java.util.List;


public interface CategoryService {
    public Category getCategoryById(Long id);
    public Category createCategory(CategoryDTO category);
    public Category updateCategory(Long categoryid, CategoryDTO category);
    public void deleteCategory(Long id);
    public List<Category> getAllCategories();
}

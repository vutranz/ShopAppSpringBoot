package com.project.shopapp.Service.Impl;

import com.project.shopapp.DTO.CategoryDTO;
import com.project.shopapp.Models.Category;
import com.project.shopapp.Repository.CategoryRepository;
import com.project.shopapp.Service.CategoryService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;



    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);

    }

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO){
        Category category =  Category.builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long categoryid,  CategoryDTO categoryDTO){
        Category existingCategory = getCategoryById(categoryid);
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }


}

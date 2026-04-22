package com.Ecommerce.Project.service;

import com.Ecommerce.Project.model.Category;
import com.Ecommerce.Project.reprositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl  implements CategoryService{
    //private List<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);

    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
//        List<Category> categories = categoryRepository.findAll();
//        Category categoryStream = categories.stream().filter(c->c.getCategoryId().equals(category)).
//                findFirst().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
          categoryRepository.delete(category);
        return "Category id is "+categoryId+"Deleted Sucessfully";

    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
//        List<Category> categories = categoryRepository.findAll();insteadodd this we has optamized code
        Optional<Category> savedCategoryOptional =categoryRepository.findById(categoryId);
        Category savedCategory = savedCategoryOptional.
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not Found Resources"));
        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;


//        Optional<Category> optionalCategory = categories.stream().filter(c->c.getCategoryId().equals(categoryId)).
//                findFirst();
//        if(optionalCategory.isPresent()){
//            Category existingCategory = optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            Category savedCategory = categoryRepository.save(existingCategory);
//            return savedCategory;
//
//        }else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found");
//        }


    }


}


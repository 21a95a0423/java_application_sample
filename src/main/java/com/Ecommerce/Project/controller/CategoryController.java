package com.Ecommerce.Project.controller;

import com.Ecommerce.Project.model.Category;
import com.Ecommerce.Project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("/api")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/public/categories")
    private List<Category>getAllCategories(){
        System.out.println("GetMethod");
        return  categoryService.getAllCategories();

    }
    @PostMapping("public/categories")
    public  String createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        System.out.println("Post");
        return "category added Successfully";
    }
     @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {//till now response is okay but status codes are wrong like if you give out of number it show the ok 200 code
         try {
             String status = categoryService.deleteCategory(categoryId);//Control the status codes
             System.out.println("Delete Method");
             return new ResponseEntity<>(status, HttpStatus.OK);
         } catch (ResponseStatusException e) {
             return new ResponseEntity<>(e.getReason(), e.getStatusCode());
         }
     }
    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody  Category category,
                                                 @PathVariable Long categoryId) {
        try {
            Category savedCategory  = categoryService.updateCategory(category, categoryId);//Control the status codes
            System.out.println("update  Method");
            return new ResponseEntity<>("category with  category id : "+categoryId, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }


}

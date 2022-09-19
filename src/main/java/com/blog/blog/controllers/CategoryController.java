package com.blog.blog.controllers;

import com.blog.blog.payloads.ApiResponse;
import com.blog.blog.payloads.CategoryDto;
import com.blog.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateCategory() {
        return categoryService.updateCategory();
    }

    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity update(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId ){
        return categoryService.updateCategory(categoryDto,categoryId);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> GetAllCategory() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Integer categoryId) {
        return categoryService.getCategoryById(categoryId);
    }
}

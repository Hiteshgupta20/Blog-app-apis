package com.blog.blog.services.impl;

import com.blog.blog.entities.CategoryEntity;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.payloads.ApiResponse;
import com.blog.blog.payloads.CategoryDto;
import com.blog.blog.repositories.CategoryRepo;
import com.blog.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<ApiResponse> createCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = modelMapper.map(categoryDto, CategoryEntity.class);
        CategoryEntity savedCategoryEntity = categoryRepo.save(categoryEntity);
        return ApiResponse.success(200, "Category Saved Successfully", this.modelMapper.map(savedCategoryEntity, CategoryDto.class));
    }

    @Override
    public ResponseEntity<ApiResponse> updateCategory() {
//        System.out.println(categoryId);
//        System.out.println(categoryDto.getCategoryDescription());
//        System.out.println(categoryDto.getCategoryTitle());

        CategoryEntity categoryEntity = categoryRepo.updateCategory();

        System.out.println(categoryEntity+"CAtegory Entity");
        if(categoryEntity == null) return ApiResponse.failure(400,"Failed",null);
//        if(categoryEntity.getCategoryTitle() == null) System.out.println("title ");
//        categoryEntity.setCategoryTitle(categoryDto.getCategoryTitle());
//        categoryEntity.setCategoryDescription(categoryDto.getCategoryDescription());
        CategoryEntity updateCategoryEntity = categoryRepo.save(categoryEntity);
        System.out.println(updateCategoryEntity);
        return ApiResponse.success(200, "Category Updated Successfully", categoryEntity);
    }

    @Override
    public ResponseEntity updateCategory(CategoryDto categoryDto, Integer categoryId) {
       CategoryEntity categoryEntity = categoryRepo.findById(categoryId).get();
        System.out.println(categoryEntity);
       if(categoryEntity == null) return  ApiResponse.failure(400,"Category not found",null);
        categoryEntity.setCategoryTitle(categoryDto.getCategoryTitle());
        categoryEntity.setCategoryDescription(categoryDto.getCategoryDescription());
        CategoryEntity updatedCategory = categoryRepo.save(categoryEntity);
        return ApiResponse.success(200,"Category Updated Successfully",updatedCategory);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteCategory(Integer categoryId) {
        CategoryEntity categoryEntity = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("CategoryEntity", "Id", categoryId));
        categoryRepo.delete(categoryEntity);
        return ApiResponse.success(200, "Category Deleted Successfully", null);

    }

    @Override
    public ResponseEntity<ApiResponse> getCategoryById(Integer categoryId) {
        CategoryEntity categoryEntity = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("CategoryEntity", "Id", categoryId));
        return ApiResponse.success(200, "Category Fetched Successfully", this.modelMapper.map(categoryEntity, CategoryDto.class));

    }

    @Override
    public ResponseEntity<ApiResponse> getAllCategories() {
        List<CategoryEntity> categoryEntity = categoryRepo.findAll();
        List<CategoryDto> categoryList = categoryEntity.stream().map(categories -> modelMapper.map(categories, CategoryDto.class)).collect(Collectors.toList());
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryTitle("Hitesh");
        categoryDto.setCategoryDescription("hvhgsvdghacdgacGS");

       String response= restTemplate.postForObject("http://localhost:8080/api/category/create", categoryDto, String.class);
        System.out.println(response);
        return ApiResponse.success(200, "Categories Fetched Successfully", categoryList);
    }

}

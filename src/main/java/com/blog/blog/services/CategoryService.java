package com.blog.blog.services;

import com.blog.blog.payloads.ApiResponse;
import com.blog.blog.payloads.CategoryDto;
import org.springframework.http.ResponseEntity;

import javax.xml.ws.Response;
import java.util.List;

public interface CategoryService {
	
	//create
	
	public ResponseEntity<ApiResponse> createCategory(CategoryDto categoryDto);
	//update
	public ResponseEntity<ApiResponse> updateCategory();
	public ResponseEntity updateCategory(CategoryDto categoryDto, Integer categoryId);

	//delete
	public ResponseEntity<ApiResponse> deleteCategory(Integer categoryId);

	//get
	public ResponseEntity<ApiResponse> getCategoryById(Integer categoryId);

	//getAll
	public ResponseEntity<ApiResponse> getAllCategories();

}

package com.blog.blog.services;

import java.util.List;

import com.blog.blog.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	//delete
	public void deleteCategory(Integer categoryId);

	//get
	public CategoryDto getCategoryById(Integer categoryId);

	//getAll
	public List<CategoryDto> getAllCategories();

}

package com.blog.blog.repositories;

import com.blog.blog.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {
    @Query(value = " UPDATE blog_app_apis.category SET category_description='hbjffgfg', category_title='ghfghhjhghgh' WHERE category_id=14",nativeQuery = true)
    CategoryEntity updateCategory();

}

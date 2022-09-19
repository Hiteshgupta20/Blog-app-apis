package com.blog.blog.repositories;

import com.blog.blog.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer>{

}

package com.blog.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;
    @NotEmpty(message = "Title must not be empty")
    private String categoryTitle;
    @NotEmpty(message = "Description must not be empty")
    private String categoryDescription;
}

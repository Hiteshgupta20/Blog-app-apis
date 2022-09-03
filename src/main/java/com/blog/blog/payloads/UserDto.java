package com.blog.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty @Size(min = 4,message = "username must be min of 4 characters")
	private String name;
	
	@Email(message = "Email is not valid!!")
	private String email;
	
	@NotEmpty(message = "Password must not be blank")
	private String password;
	
	@NotEmpty(message = "About must not be blank" )
	private String about;

}

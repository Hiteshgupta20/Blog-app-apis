package com.blog.blog.exceptions;

import com.blog.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandle {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message= ex.getMessage();
		return ApiResponse.failure(400,message,null);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> methodNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> resp= new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			String fieldName = ((FieldError) error).getField();
			String message= error.getDefaultMessage();
			resp.put(fieldName, message);
 		});
		return ApiResponse.failure(400,"Failed",resp);
	}

}

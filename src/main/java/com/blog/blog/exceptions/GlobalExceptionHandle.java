package com.blog.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandle {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message= ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(400, message, null);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> methodNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> resp= new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			String fieldName = ((FieldError) error).getField();
			String message= error.getDefaultMessage();
			resp.put(fieldName, message);
 		});
		ApiResponse apiResponse = new ApiResponse(400, "Failed", resp);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}

}

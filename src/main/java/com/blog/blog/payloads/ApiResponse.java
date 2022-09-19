package com.blog.blog.payloads;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

	private int statusCode;
	private String message;
	private Object object;

	public static ResponseEntity<ApiResponse> success(int statusCode, String message, Object object){
		return  ResponseEntity.ok().body(new ApiResponse(statusCode,message,object));

	}

	public static ResponseEntity<ApiResponse> failure(int statusCode, String message, Object errorDescription){
		return  ResponseEntity.ok().body(new ApiResponse(statusCode,message,errorDescription));

	}
	
	
}

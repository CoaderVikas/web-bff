/*
 * package com.bff.vikas.exception;
 * 
 * import java.time.LocalDateTime; import java.util.HashMap; import
 * java.util.Map;
 * 
 * import org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.validation.FieldError; import
 * org.springframework.web.bind.MethodArgumentNotValidException; import
 * org.springframework.web.bind.annotation.ExceptionHandler; import
 * org.springframework.web.bind.annotation.RestControllerAdvice;
 * 
 * import com.bff.vikas.feign.dto.response.ApiError;
 * 
 * import jakarta.servlet.http.HttpServletRequest; import
 * lombok.extern.slf4j.Slf4j;
 * 
 *//**
	 * Class : GlobalExceptionHandler Description: [Add brief description here]
	 * Author : Vikas Yadav Created On : Apr 20, 2026 Version : 1.0
	 *//*
		 * 
		 * @RestControllerAdvice
		 * 
		 * @Slf4j public class GlobalExceptionHandler {
		 * 
		 * @ExceptionHandler(BffServiceException.class) public ResponseEntity<ApiError>
		 * handleBffException(BffServiceException ex, HttpServletRequest request) {
		 * ApiError error = ex.getApiError();
		 * 
		 * if (error.getPath() == null) error.setPath(request.getRequestURI());
		 * 
		 * // 1. Load balancer check if (error.getMessage() != null &&
		 * error.getMessage().contains("Load balancer")) { error.
		 * setMessage("Service is temporarily unavailable, please try again later."); }
		 * // 2. Optimized Duplicate Key Message else if (error.getMessage() != null &&
		 * error.getMessage().contains("duplicate key value")) { // Regex pattern jo
		 * extract karega: (property_id)=(PRO260623P) // Hum replaceAll ka use karke
		 * symbols hata denge String rawMsg = error.getMessage(); if
		 * (rawMsg.contains("property_id")) { String id =
		 * rawMsg.substring(rawMsg.indexOf("(property_id)=(") + 15,
		 * rawMsg.indexOf(") already")); error.setMessage("Property ID " + id +
		 * " already exists."); } }
		 * 
		 * log.error("BFF Error: {} | Status: {}", error.getMessage(),
		 * error.getStatus()); return new ResponseEntity<>(error,
		 * HttpStatus.valueOf(error.getStatus())); }
		 * 
		 * @ExceptionHandler(MethodArgumentNotValidException.class) public
		 * ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex,
		 * HttpServletRequest request) { String firstError =
		 * ex.getBindingResult().getFieldErrors().stream().map(FieldError::
		 * getDefaultMessage) .findFirst().orElse("Validation failed");
		 * 
		 * ApiError error =
		 * ApiError.builder().message(firstError).status(HttpStatus.BAD_REQUEST.value())
		 * .path(request.getRequestURI()).timestamp(LocalDateTime.now().toString()).
		 * errorCode("VALIDATION_FAILED") .build();
		 * 
		 * return ResponseEntity.badRequest().body(error); } }
		 */
package com.bff.vikas.feign.client;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.bff.vikas.config.FeignConfig;
import com.bff.vikas.feign.dto.request.PropertyCreateRequest;
import com.bff.vikas.feign.dto.request.PropertySearchRequest;
import com.bff.vikas.feign.dto.request.PropertyUpdateRequest;
import com.bff.vikas.feign.dto.response.PropertyPageResponse;
import com.bff.vikas.feign.dto.response.PropertyResponse;

/**
 * Class      : PropertyServiceFeignClient
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 29, 2026
 * Version    : 1.0
 */

@FeignClient(name = "PROPERTY-SERVICE",path = "/properties",configuration = FeignConfig.class)
public interface PropertyServiceFeignClient {
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping
	ResponseEntity<PropertyResponse> createProperty(@RequestBody PropertyCreateRequest request);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	ResponseEntity<PropertyResponse> getPropertyById(@PathVariable("id") UUID id);

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/search")
	ResponseEntity<PropertyPageResponse> searchProperties(@RequestBody PropertySearchRequest request);

	/**
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@PutMapping("/{id}")
	ResponseEntity<PropertyResponse> updateProperty(@PathVariable("id") UUID id,@RequestBody PropertyUpdateRequest request);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteProperty(@PathVariable("id") UUID id);
	/**
	 * Upload property image
	 */
	@PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	String uploadPropertyImage(@PathVariable("id") UUID propertyId,@RequestPart("file") MultipartFile file);

	/**
	 * Get total property count
	 */
	@GetMapping("/count")
	Long getTotalProperties(@RequestHeader("Authorization") String token);
	/**
	 * 
	 * @return
	 */
	@GetMapping("/me")
    List<PropertyResponse> getMyProperties();
}

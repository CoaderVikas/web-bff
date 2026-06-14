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
import com.bff.vikas.feign.dto.request.PropertyRequestDTO;
import com.bff.vikas.feign.dto.response.PropertyResponseDTO;

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
	//ResponseEntity<PropertyResponse> createProperty(@RequestBody PropertyCreateRequest request);
	ResponseEntity<PropertyResponseDTO> createProperty(@RequestBody PropertyRequestDTO request);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{propertyId}")
	ResponseEntity<PropertyResponseDTO> getPropertyById(@PathVariable("propertyId") String propertyId);

	/**
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@PutMapping("/{propertyId}")
	ResponseEntity<PropertyResponseDTO> updateProperty(@PathVariable("propertyId") String propertyId, @RequestBody PropertyRequestDTO requestDTO);
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/me")
	List<PropertyResponseDTO> getMyProperties();
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{propertyId}")
	ResponseEntity<String> deleteProperty(@PathVariable("propertyId") String propertyId);

	/**
	 * Get total property count
	 */
	@GetMapping("/count")
	Long getTotalProperties(@RequestHeader("Authorization") String token);
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	//@PostMapping("/search")
	//ResponseEntity<PropertyPageResponse> searchProperties(@RequestBody PropertySearchRequest request);
	
	/**
	 * Upload property image
	 */
	@PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	String uploadPropertyImage(@PathVariable("id") UUID propertyId,@RequestPart("file") MultipartFile file);
}

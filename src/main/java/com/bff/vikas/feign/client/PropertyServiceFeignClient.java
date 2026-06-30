package com.bff.vikas.feign.client;

import java.util.List;

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
import com.bff.vikas.feign.dto.request.PropertyUpdateRequestDTO;
import com.bff.vikas.feign.dto.response.PropertyResponseDTO;

@FeignClient(name = "PROPERTY-SERVICE", path = "/properties", configuration = FeignConfig.class)
public interface PropertyServiceFeignClient {

	/**
	 * Multipart create — 'property' part = JSON string, 'images' part = files.
	 */
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseEntity<PropertyResponseDTO> createPropertyWithImages(
			@RequestPart("property") String propertyJson,
			@RequestPart(value = "images", required = false) List<MultipartFile> images);

	/**
	 * Backward-compatible JSON-only create.
	 */
	@PostMapping
	ResponseEntity<PropertyResponseDTO> createProperty(@RequestBody PropertyRequestDTO request);

	@GetMapping("/{propertyId}")
	ResponseEntity<PropertyResponseDTO> getPropertyById(@PathVariable("propertyId") String propertyId);

	@PutMapping("/{propertyId}")
	ResponseEntity<PropertyResponseDTO> updateProperty(@PathVariable("propertyId") String propertyId,
			@RequestBody PropertyRequestDTO requestDTO);

	@GetMapping("/me")
	List<PropertyResponseDTO> getMyProperties();

	@DeleteMapping("/{propertyId}")
	ResponseEntity<String> deleteProperty(@PathVariable("propertyId") String propertyId);

	@GetMapping("/count")
	Long getTotalProperties(@RequestHeader("Authorization") String token);

	@GetMapping("/{propertyId}/exists")
	ResponseEntity<Boolean> checkPropertyExists(@PathVariable("propertyId") String propertyId);

	@PutMapping("/{propertyId}/allocate-property")
	ResponseEntity<?> syncPropertyOccupancy(@PathVariable("propertyId") String propertyId,@RequestBody PropertyUpdateRequestDTO request);
}
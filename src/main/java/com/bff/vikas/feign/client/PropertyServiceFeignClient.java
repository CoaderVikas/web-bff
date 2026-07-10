package com.bff.vikas.feign.client;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.bff.vikas.config.FeignConfig;
import com.bff.vikas.feign.dto.request.PropertyRequestDTO;
import com.bff.vikas.feign.dto.request.PropertyUpdateRequestDTO;
import com.bff.vikas.feign.dto.request.VerificationActionRequest;
import com.bff.vikas.feign.dto.response.PagedResponse;
import com.bff.vikas.feign.dto.response.PropertyResponseDTO;

@FeignClient(name = "PROPERTY-SERVICE", path = "/properties", configuration = FeignConfig.class)
public interface PropertyServiceFeignClient {

	/**
	 * Multipart create - 'property' part = JSON string, 'images' part = files.
	 */
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseEntity<PropertyResponseDTO> createPropertyWithImages(@RequestPart("property") String propertyJson,
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

	// NEW: paginated portfolio
	@GetMapping("/me/paged")
	PagedResponse<PropertyResponseDTO> getMyPropertiesPaged(@RequestParam("page") int page,
			@RequestParam("size") int size);

	// NEW: dynamic paginated search (all filters optional)
	@GetMapping("/search")
	PagedResponse<PropertyResponseDTO> searchProperties(@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "buildingType", required = false) String buildingType,
			@RequestParam(value = "furnishing", required = false) String furnishing,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "minRent", required = false) BigDecimal minRent,
			@RequestParam(value = "maxRent", required = false) BigDecimal maxRent,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size);

	@DeleteMapping("/{propertyId}")
	ResponseEntity<String> deleteProperty(@PathVariable("propertyId") String propertyId);

	// NEW: restore a soft-deleted property
	@PutMapping("/{propertyId}/restore")
	ResponseEntity<PropertyResponseDTO> restoreProperty(@PathVariable("propertyId") String propertyId);

	// =========================================================================
	// NEW: Verification workflow (admin-gated in Property Service)
	// =========================================================================

	// Admin approves a property (no body).
	@PutMapping("/{propertyId}/verify")
	ResponseEntity<PropertyResponseDTO> verifyProperty(@PathVariable("propertyId") String propertyId);

	// Admin rejects a property (body: { "reason": "..." }).
	@PutMapping("/{propertyId}/reject")
	ResponseEntity<PropertyResponseDTO> rejectProperty(@PathVariable("propertyId") String propertyId,
			@RequestBody VerificationActionRequest request);

	// Admin review queue by status (PENDING / VERIFIED / REJECTED).
	@GetMapping("/verification-queue")
	PagedResponse<PropertyResponseDTO> getVerificationQueue(
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size);

	@GetMapping("/count")
	Long getTotalProperties(@RequestHeader("Authorization") String token);

	@GetMapping("/{propertyId}/exists")
	ResponseEntity<Boolean> checkPropertyExists(@PathVariable("propertyId") String propertyId);

	@PutMapping("/{propertyId}/allocate-property")
	ResponseEntity<?> syncPropertyOccupancy(@PathVariable("propertyId") String propertyId,
			@RequestBody PropertyUpdateRequestDTO request);

	@GetMapping("/all")
	List<PropertyResponseDTO> findAllProperties();
}
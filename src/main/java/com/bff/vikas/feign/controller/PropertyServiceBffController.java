package com.bff.vikas.feign.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bff.vikas.feign.dto.request.PropertyRequestDTO;
import com.bff.vikas.feign.dto.request.PropertyUpdateRequestDTO;
import com.bff.vikas.feign.dto.response.PropertyResponseDTO;
import com.bff.vikas.feign.service.PropertyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/properties")
@AllArgsConstructor
@Slf4j
@Tag(name = "Property Service APIs", description = "Endpoints for Property Management (Create, Update, Search, Delete)")
public class PropertyServiceBffController {

	private final PropertyService propertyService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create Property", description = "Creates a new property listing (no images).")
	public ResponseEntity<PropertyResponseDTO> createProperty(@Valid @RequestBody PropertyRequestDTO request) {
		log.info("BFF: Create Property");
		PropertyResponseDTO property = propertyService.createProperty(request);
		return (property != null) ? ResponseEntity.ok(property) : ResponseEntity.internalServerError().build();
	}

	/**
	 * Multipart create — images ke saath.
	 */
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create Property With Images", description = "Creates a new property listing along with images.")
	public ResponseEntity<PropertyResponseDTO> createPropertyWithImages(
			@RequestPart("property") @Valid PropertyRequestDTO request,
			@RequestPart(value = "images", required = false) List<MultipartFile> images) {

		log.info("BFF: Create Property With Images | imageCount={}", images == null ? 0 : images.size());
		PropertyResponseDTO property = propertyService.createPropertyWithImages(request, images);
		return (property != null)
				? new ResponseEntity<>(property, HttpStatus.CREATED)
				: ResponseEntity.internalServerError().build();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get Property By ID", description = "Fetch property details using property ID.")
	public ResponseEntity<PropertyResponseDTO> getPropertyById(@PathVariable("id") String id) {
		log.info("BFF: Get Property | id={}", id);
		PropertyResponseDTO property = propertyService.getProperty(id);
		return (property != null) ? ResponseEntity.ok(property) : ResponseEntity.internalServerError().build();
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update Property", description = "Updates an existing property.")
	public ResponseEntity<PropertyResponseDTO> updateProperty(@PathVariable("id") String id,
			@RequestBody PropertyRequestDTO request) {
		log.info("BFF: Update Property | id={}", id);
		PropertyResponseDTO updated = propertyService.updateProperty(id, request);
		return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.internalServerError().build();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Property", description = "Deletes a property by ID.")
	public ResponseEntity<String> deleteProperty(@PathVariable("id") String id) {
		log.info("BFF: Delete Property | id={}", id);
		String result = propertyService.deleteProperty(id);
		return !ObjectUtils.isEmpty(result) ? ResponseEntity.ok(result) : ResponseEntity.internalServerError().build();
	}

	@GetMapping("/count")
	@Operation(summary = "Get Total Properties", description = "Returns total active properties count")
	public ResponseEntity<Long> getTotalProperties(@RequestHeader("Authorization") String token) {
		log.info("BFF: Get Total Properties Count");
		Long count = propertyService.getTotalProperties(token);
		return count != null ? ResponseEntity.ok(count) : ResponseEntity.internalServerError().build();
	}

	@GetMapping("/me")
	@Operation(summary = "Get My Properties", description = "Returns properties owned by the current session user.")
	public ResponseEntity<List<PropertyResponseDTO>> getMyProperties() {
		log.info("BFF: Get My Properties");
		return ResponseEntity.ok(propertyService.getMyProperties());
	}

	@GetMapping("/{id}/exists")
	@Operation(summary = "Check Property Exists", description = "Checks whether a property exists by ID.")
	public ResponseEntity<Boolean> checkPropertyExists(@PathVariable("id") String id) {
		log.info("BFF: Check Property Exists | id={}", id);
		Boolean exists = propertyService.checkPropertyExists(id);
		return ResponseEntity.ok(exists);
	}

	@PutMapping("/{id}/allocate-property")
	@Operation(summary = "Allocate/Vacate Property", description = "Allocates or vacates a property unit.")
	public ResponseEntity<?> syncPropertyOccupancy(@PathVariable("id") String id,
			@RequestBody PropertyUpdateRequestDTO request) {
		log.info("BFF: Allocate/Vacate Property | id={}", id);
		return propertyService.syncPropertyOccupancy(id, request);
	}
}
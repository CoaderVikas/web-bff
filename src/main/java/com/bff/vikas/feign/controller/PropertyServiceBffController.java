package com.bff.vikas.feign.controller;

import java.util.List;
import java.util.UUID;

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
import com.bff.vikas.feign.dto.response.PropertyResponseDTO;
import com.bff.vikas.feign.service.PropertyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class      : PropertyServiceBffController
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 29, 2026
 * Version    : 1.0
 */

@RestController
@RequestMapping(value = "/rent-hub/api/v1/properties")
@AllArgsConstructor
@Slf4j
@Tag(name = "Property Service APIs", description = "Endpoints for Property Management (Create, Update, Search, Delete)")
public class PropertyServiceBffController {

	private final PropertyService propertyService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create Property", description = "Creates a new property listing.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Property created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request data") })
	public ResponseEntity<PropertyResponseDTO> createProperty(@Valid @RequestBody PropertyRequestDTO request) {

		log.info("BFF: Create Property");

		PropertyResponseDTO property = propertyService.createProperty(request);

		return (property != null)
				? ResponseEntity.ok(property)
				: ResponseEntity.internalServerError().build();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get Property By ID", description = "Fetch property details using property ID.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Property retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Property not found") })
	public ResponseEntity<PropertyResponseDTO> getPropertyById(@PathVariable("id") String id) {

		log.info("BFF: Get Property | id={}", id);

		PropertyResponseDTO property = propertyService.getProperty(id);

		return (property != null)
				? ResponseEntity.ok(property)
				: ResponseEntity.internalServerError().build();
	}

	/*
	 * @PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE,
	 * produces = MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @Operation(summary = "Search Properties", description =
	 * "Search properties with filters and pagination.")
	 * 
	 * @ApiResponses({ @ApiResponse(responseCode = "200", description =
	 * "Properties fetched successfully") }) public
	 * ResponseEntity<PropertyPageResponse> searchProperties(@RequestBody
	 * PropertySearchRequest request) {
	 * 
	 * log.info("BFF: Search Properties | request={}", request);
	 * 
	 * PropertyPageResponse response = propertyService.searchProperties(request);
	 * 
	 * return (response != null) ? ResponseEntity.ok(response) :
	 * ResponseEntity.internalServerError().build(); }
	 */

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update Property", description = "Updates an existing property.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Property updated successfully"),
			@ApiResponse(responseCode = "404", description = "Property not found") })
	public ResponseEntity<PropertyResponseDTO> updateProperty(@PathVariable("id") String id,
			@RequestBody PropertyRequestDTO request) {

		log.info("BFF: Update Property | id={}", id);

		PropertyResponseDTO updated = propertyService.updateProperty(id, request);

		return (updated != null)
				? ResponseEntity.ok(updated)
				: ResponseEntity.internalServerError().build();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Property", description = "Deletes a property by ID.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Property deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Property not found") })
	public ResponseEntity<String> deleteProperty(@PathVariable("id") String id) {

		log.info("BFF: Delete Property | id={}", id);

		String result = propertyService.deleteProperty(id);

		return !ObjectUtils.isEmpty(result)
				? ResponseEntity.ok(result)
				: ResponseEntity.internalServerError().build();
	}

	@PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "Upload Property Image", description = "Uploads image for a property")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Image uploaded successfully"),
			@ApiResponse(responseCode = "404", description = "Property not found") })
	public ResponseEntity<String> uploadPropertyImage(@PathVariable("id") UUID id,
			@RequestPart("file") MultipartFile file) {

		log.info("BFF: Upload Property Image | id={} | fileSize={}", id, file.getSize());

		String imagePath = propertyService.uploadPropertyImage(id, file);

		return !ObjectUtils.isEmpty(imagePath) ? ResponseEntity.ok(imagePath)
				: ResponseEntity.internalServerError().build();
	}

	@GetMapping("/count")
	@Operation(summary = "Get Total Properties", description = "Returns total active properties count")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Count fetched successfully") })
	public ResponseEntity<Long> getTotalProperties(@RequestHeader("Authorization") String token) {

		log.info("BFF: Get Total Properties Count");

		Long count = propertyService.getTotalProperties(token);

		return count != null ? ResponseEntity.ok(count) : ResponseEntity.internalServerError().build();
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/me")
	public ResponseEntity<List<PropertyResponseDTO>> getMyProperties() {
	    return ResponseEntity.ok(propertyService.getMyProperties());
	}
}
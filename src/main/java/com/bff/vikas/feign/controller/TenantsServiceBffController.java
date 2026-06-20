package com.bff.vikas.feign.controller;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bff.vikas.feign.dto.request.ActiveAllocationDetails;
import com.bff.vikas.feign.dto.request.AllocationRequestDto;
import com.bff.vikas.feign.dto.request.FeedbackRequestDto;
import com.bff.vikas.feign.dto.request.PropertyAllocationResponseDto;
import com.bff.vikas.feign.dto.request.TenantRegistrationDto;
import com.bff.vikas.feign.dto.request.TenantResponseDto;
import com.bff.vikas.feign.dto.request.TenantUpdateRequestDto;
import com.bff.vikas.feign.dto.request.VacateRequestDto;
import com.bff.vikas.feign.dto.response.FeedbackResponseDto;
import com.bff.vikas.feign.service.TenantsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class       : TenantsServiceBffController
 * Description : BFF Controller for Tenant Management and Allocation operations.
 * Author      : Vikas Yadav
 * Created On  : Jun 17, 2026
 * Version     : 1.2
 */
@RestController
@RequestMapping(value = "/tenants")
@AllArgsConstructor
@Slf4j
@Tag(name = "Tenant Management BFF", description = "Endpoints for Tenant operations")
@Tag(name = "Tenant Service APIs", description = "Endpoints for Tenant Allocation and Management")
public class TenantsServiceBffController {

	private final TenantsService tenantsService;

	@Operation(summary = "Tenant Self Registration")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Created"),
			@ApiResponse(responseCode = "400", description = "Bad Request") })
	@PostMapping(value = "/register/self")
	public ResponseEntity<TenantResponseDto> registerTenantSelf(@Valid @RequestBody TenantRegistrationDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tenantsService.registerTenantSelf(dto));
	}

	@Operation(summary = "Owner Tenant Registration")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Created"),
			@ApiResponse(responseCode = "403", description = "Forbidden") })
	@PostMapping(value = "/register/by-owner")
	public ResponseEntity<TenantResponseDto> registerTenantByOwner(@Valid @RequestBody TenantRegistrationDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tenantsService.registerTenantByOwner(dto));
	}

	@Operation(summary = "Verify Tenant")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	@GetMapping("/verify/{input}")
	public ResponseEntity<TenantResponseDto> verifyTenant(@PathVariable("input") String input) {
		return ResponseEntity.ok(tenantsService.verifyTenant(input));
	}

	@Operation(summary = "Allocate Room")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Allocated Successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid Allocation Request") })
	@PostMapping("/allot")
	public ResponseEntity<Map<String, Object>> allocateRoomOrBed(@Valid @RequestBody AllocationRequestDto dto) {
		return ResponseEntity.ok(tenantsService.allocateRoomOrBed(dto));
	}

	@Operation(summary = "Vacate Room")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Vacated Successfully"),
			@ApiResponse(responseCode = "404", description = "Allocation Not Found") })
	@PostMapping("/vacate")
	public ResponseEntity<Map<String, Object>> vacateRoomOrBed(@RequestBody VacateRequestDto dto) {
		return ResponseEntity.ok(tenantsService.vacateRoomOrBed(dto));
	}

	@Operation(summary = "Get Property History")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No History Found") })
	@GetMapping("/{propertyId}/allocations")
	public ResponseEntity<PropertyAllocationResponseDto> getHistoryOfTenants(@PathVariable("propertyId") String propertyId) {
		return ResponseEntity.ok(tenantsService.getPropertyAllocations(propertyId));
	}

	@Operation(summary = "Get Active Allocation")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "No Active Allocation") })
	@PostMapping("/active-info")
	public ResponseEntity<List<ActiveAllocationDetails>> getActiveAllocation(@RequestBody VacateRequestDto dto) {
		return ResponseEntity.ok(tenantsService.getActiveAllocation(dto));
	}

	@Operation(summary = "Update Tenant")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Updated Successfully"),
			@ApiResponse(responseCode = "404", description = "Tenant Not Found") })
	@PutMapping(value = "/update/{customId}")
	public ResponseEntity<TenantResponseDto> updateTenantDataOnly(@PathVariable("customId") String customId,
			@Valid @RequestBody TenantUpdateRequestDto dto) {
		return ResponseEntity.ok(tenantsService.updateTenantData(customId, dto));
	}

	@Operation(summary = "Admin: Get All Tenants")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No Tenants Found") })
	@GetMapping("/admin/all")
	public ResponseEntity<List<TenantResponseDto>> getAllTenantsForAdmin() {
		List<TenantResponseDto> list = tenantsService.getAllTenantsForAdmin();
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}

	@Operation(summary = "Owner: Get My Tenants")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No Tenants Found") })
	@GetMapping("/my-tenants")
	public ResponseEntity<List<TenantResponseDto>> getMyTenants() {
		List<TenantResponseDto> list = tenantsService.getMyTenants();
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}

	@Operation(summary = "Get Tenant Details")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Tenant Not Found") })
	@GetMapping("/{tenantCustomId}")
	public ResponseEntity<TenantResponseDto> getTenantByCustomId(@PathVariable("tenantCustomId") String tenantCustomId) {
		return ResponseEntity.ok(tenantsService.getTenantByCustomId(tenantCustomId));
	}

	@Operation(summary = "Submit Feedback")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Feedback Submitted"),
			@ApiResponse(responseCode = "400", description = "Bad Request") })
	@PostMapping("/feedback")
	public ResponseEntity<FeedbackResponseDto> submitFeedback(@Valid @RequestBody FeedbackRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tenantsService.submitFeedback(dto));
	}

	@Operation(summary = "Get Tenant Feedback History")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No Feedback Found") })
	@GetMapping(value = "/{tenantCustomId}/feedback", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FeedbackResponseDto>> getFeedbackHistory(@PathVariable("tenantCustomId") String tenantCustomId) {
		log.info("BFF: Fetching feedback history for customId={}", tenantCustomId);
		List<FeedbackResponseDto> response = tenantsService.getFeedbackHistory(tenantCustomId);
		if (response == null || response.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Upload Tenant Image")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Image Uploaded"),
			@ApiResponse(responseCode = "400", description = "Invalid File") })
	@PutMapping(value = "/uploadImage/{customId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<TenantResponseDto> updateImageProfile(@PathVariable("customId") String customId,
			@RequestParam(value = "file") MultipartFile file) {
		return ResponseEntity.ok(tenantsService.updateTenantImage(customId, file));
	}
	
	@Operation(summary = "Get Tenant Image")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Image Not Found") })
	@GetMapping("/getImage/{customId}")
	public ResponseEntity<Resource> getTenantImage(@PathVariable("customId") String customId) {
		return ResponseEntity.ok(tenantsService.getTenantImage(customId));
	}
}
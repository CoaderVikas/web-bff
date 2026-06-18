package com.bff.vikas.feign.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bff.vikas.feign.dto.request.ActiveAllocationDetails;
import com.bff.vikas.feign.dto.request.AllocationRequestDto;
import com.bff.vikas.feign.dto.request.PropertyAllocationResponseDto;
import com.bff.vikas.feign.dto.request.TenantRegistrationDto;
import com.bff.vikas.feign.dto.request.TenantResponseDto;
import com.bff.vikas.feign.dto.request.VacateRequestDto;
import com.bff.vikas.feign.service.TenantsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class      : TenantsServiceController
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 17, 2026
 * Version    : 1.0
 */

@RestController
@RequestMapping(value = "/tenants")
@AllArgsConstructor
@Slf4j
@Tag(name = "Tenant Service APIs", description = "Endpoints for Tenant Allocation and Management")
public class TenantsServiceBffController {

	private final TenantsService tenantsService;

	@GetMapping(value = "/verify/{contact}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Verify Tenant", description = "Verify tenant existence by contact number.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Tenant found"),
			@ApiResponse(responseCode = "404", description = "Tenant not found") })
	public ResponseEntity<TenantResponseDto> verifyTenant(@PathVariable("contact") String contact) {
		log.info("BFF: Verify Tenant | contact={}", contact);
		TenantResponseDto response = tenantsService.verifyTenant(contact);
		return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
	}

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Register Tenant", description = "Registers a new tenant.")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Tenant registered successfully") })
	public ResponseEntity<TenantResponseDto> registerTenant(@Valid @RequestBody TenantRegistrationDto request) {
		log.info("BFF: Register Tenant");
		TenantResponseDto response = tenantsService.registerTenant(request);
		return (response != null) ? ResponseEntity.status(201).body(response)
				: ResponseEntity.internalServerError().build();
	}

	@PostMapping(value = "/allot", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Allocate Property", description = "Allocates a room or bed to a tenant.")
	public ResponseEntity<Map<String, Object>> allocateRoomOrBed(@RequestBody AllocationRequestDto request) {
		log.info("BFF: Allocate Room/Bed");
		Map<String, Object> response = tenantsService.allocateRoomOrBed(request);
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/vacate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Vacate Property", description = "Vacates an allocated room or bed.")
	public ResponseEntity<Map<String, Object>> vacateRoomOrBed(@RequestBody VacateRequestDto request) {
		log.info("BFF: Vacate Room/Bed");
		Map<String, Object> response = tenantsService.vacateRoomOrBed(request);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/properties/{propertyId}/allocations", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get Allocation History", description = "Fetch allocation history for a specific property.")
	public ResponseEntity<PropertyAllocationResponseDto> getHistoryOfTenants(
			@PathVariable("propertyId") String propertyId) {
		log.info("BFF: Get History for propertyId={}", propertyId);
		PropertyAllocationResponseDto response = tenantsService.getHistoryOfTenants(propertyId);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/active-info", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get Active Allocation Details (Lazy Load)", description = "Fetches the current live resident details for a specific floor/room/bed.")
	@ApiResponses({ 
		@ApiResponse(responseCode = "200", description = "Active allocation details retrieved successfully"),
		@ApiResponse(responseCode = "400", description = "Bad Request or No active allocation found") 
	})
	public ResponseEntity<ActiveAllocationDetails> getActiveAllocation(@RequestBody VacateRequestDto dto) {
		log.info("BFF: Fetching active allocation info for propertyId={}, roomNumber={}", dto.getPropertyId(), dto.getRoomNumber());
		ActiveAllocationDetails details = tenantsService.getActiveAllocationBypropertyInfo(dto);
		return ResponseEntity.ok(details);
	}
}
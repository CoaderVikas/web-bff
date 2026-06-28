package com.bff.vikas.feign.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bff.vikas.feign.client.AgreementFeignClient;
import com.bff.vikas.feign.dto.request.AgreementRequestDto;
import com.bff.vikas.feign.dto.response.AgreementResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class       : AgreementBffController
 * Description : BFF Controller for Agreement Management operations.
 * Author      : Vikas Yadav
 * Created On  : Jun 24, 2026
 * Version     : 1.0
 */
@RestController
@RequestMapping(value = "/agreement")
@AllArgsConstructor
@Slf4j
@Tag(name = "Agreement Management BFF", description = "Endpoints for Agreement operations")
public class AgreementBffController {

	private final AgreementFeignClient agreementFeignClient;

	@Operation(summary = "Create Agreement")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Agreement Created"),
			@ApiResponse(responseCode = "400", description = "Bad Request") })
	@PostMapping
	public ResponseEntity<AgreementResponseDto> createAgreement(@Valid @RequestBody AgreementRequestDto request) {
		log.info("BFF: Creating agreement for tenantId={}", request.getTenantId());
		return ResponseEntity.status(HttpStatus.CREATED).body(agreementFeignClient.createAgreement(request));
	}

	@Operation(summary = "Terminate Agreement")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Agreement Terminated"),
			@ApiResponse(responseCode = "404", description = "Agreement Not Found") })
	@PutMapping("/{agreementId}/terminate")
	public ResponseEntity<AgreementResponseDto> terminateAgreement(@PathVariable("agreementId") String agreementId) {
		log.info("BFF: Terminating agreementId={}", agreementId);
		return ResponseEntity.ok(agreementFeignClient.terminateAgreement(agreementId));
	}

	@Operation(summary = "Get Agreements by Owner")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No Agreements Found") })
	@GetMapping("/owner/{ownerId}")
	public ResponseEntity<List<AgreementResponseDto>> getByOwner(@PathVariable("ownerId") String ownerId) {
		log.info("BFF: Fetching agreements for ownerId={}", ownerId);
		List<AgreementResponseDto> list = agreementFeignClient.getAgreementsByOwner(ownerId);
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}

	@Operation(summary = "Get Agreements by Tenant")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No Agreements Found") })
	@GetMapping("/tenant/{tenantId}")
	public ResponseEntity<List<AgreementResponseDto>> getByTenant(@PathVariable("tenantId") String tenantId) {
		log.info("BFF: Fetching agreements for tenantId={}", tenantId);
		List<AgreementResponseDto> list = agreementFeignClient.getAgreementsByTenant(tenantId);
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}

	@Operation(summary = "Get Agreements by Property")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No Agreements Found") })
	@GetMapping("/property/{propertyId}")
	public ResponseEntity<List<AgreementResponseDto>> getByProperty(@PathVariable("propertyId") String propertyId) {
		log.info("BFF: Fetching agreements for propertyId={}", propertyId);
		List<AgreementResponseDto> list = agreementFeignClient.getAgreementsByProperty(propertyId);
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}

	@Operation(summary = "Get Active Agreements")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No Active Agreements") })
	@GetMapping("/active")
	public ResponseEntity<List<AgreementResponseDto>> getActive() {
		log.info("BFF: Fetching all active agreements");
		List<AgreementResponseDto> list = agreementFeignClient.getActiveAgreements();
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}
}
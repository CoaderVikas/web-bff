package com.bff.vikas.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bff.vikas.config.FeignConfig;
import com.bff.vikas.feign.dto.request.AgreementRequestDto;
import com.bff.vikas.feign.dto.response.AgreementResponseDto;

/**
 * Class      : AgreementFeignClient
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 24, 2026
 * Version    : 1.0
 */

@FeignClient(name = "AGREEMENT-SERVICE",path = "/api",configuration = FeignConfig.class)
public interface AgreementFeignClient {

	@PostMapping("/agreements")
	AgreementResponseDto createAgreement(@RequestBody AgreementRequestDto request);

	@PutMapping("/agreements/{agreementId}/terminate")
	AgreementResponseDto terminateAgreement(@PathVariable("agreementId") String agreementId);

	@GetMapping("/agreements/owner/{ownerId}")
	List<AgreementResponseDto> getAgreementsByOwner(@PathVariable("ownerId") String ownerId);

	@GetMapping("/agreements/tenant/{tenantId}")
	List<AgreementResponseDto> getAgreementsByTenant(@PathVariable("tenantId") String tenantId);

	@GetMapping("/agreements/property/{propertyId}")
	List<AgreementResponseDto> getAgreementsByProperty(@PathVariable("propertyId") String propertyId);

	@GetMapping("/agreements/active")
	List<AgreementResponseDto> getActiveAgreements();
}

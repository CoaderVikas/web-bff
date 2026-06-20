package com.bff.vikas.feign.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.bff.vikas.config.FeignConfig;
import com.bff.vikas.feign.dto.request.ActiveAllocationDetails;
import com.bff.vikas.feign.dto.request.AllocationRequestDto;
import com.bff.vikas.feign.dto.request.FeedbackRequestDto;
import com.bff.vikas.feign.dto.request.PropertyAllocationResponseDto;
import com.bff.vikas.feign.dto.request.TenantRegistrationDto;
import com.bff.vikas.feign.dto.request.TenantResponseDto;
import com.bff.vikas.feign.dto.request.TenantUpdateRequestDto;
import com.bff.vikas.feign.dto.request.VacateRequestDto;
import com.bff.vikas.feign.dto.response.FeedbackResponseDto;

/**
 * Class      : TenantsServiceFeignClient
 * Description: Feign client mapped to the actual TenantController endpoints
 *              exposed by the Tenant microservice (com.tenants.controller.TenantController).
 * Author     : Vikas Yadav
 * Created On : Jun 17, 2026
 * Version    : 1.1
 */
@FeignClient(name = "TENANTS-SERVICE", path = "/tenants", configuration = FeignConfig.class)
public interface TenantsServiceFeignClient {

	@PostMapping("/register/self")
	TenantResponseDto registerTenantSelf(@RequestBody TenantRegistrationDto dto);

	@PostMapping("/register/by-owner")
	TenantResponseDto registerTenantByOwner(@RequestBody TenantRegistrationDto dto);

	@GetMapping("/verify/{input}")
	TenantResponseDto verifyTenant(@PathVariable("input") String input);

	@PostMapping("/allot")
	Map<String, Object> allocateRoomOrBed(@RequestBody AllocationRequestDto dto);

	@PostMapping("/vacate")
	Map<String, Object> vacateRoomOrBed(@RequestBody VacateRequestDto dto);

	@GetMapping("/properties/{propertyId}/allocations")
	PropertyAllocationResponseDto getHistoryOfTenants(@PathVariable("propertyId") String propertyId);

	@PostMapping("/active-info")
	List<ActiveAllocationDetails> getActiveAllocation(@RequestBody VacateRequestDto dto);

	@PutMapping("/update/{customId}")
	TenantResponseDto updateTenantDataOnly(@PathVariable("customId") String customId,
			@RequestBody TenantUpdateRequestDto dto);

	@PutMapping(value = "/uploadImage/{customId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	TenantResponseDto updateImageProfile(@PathVariable("customId") String customId,
			@RequestParam("file") MultipartFile file);

	@GetMapping("/getImage/{customId}")
	Resource getTenantImage(@PathVariable("customId") String customId);

	@GetMapping("/admin/all")
	List<TenantResponseDto> getAllTenantsForAdmin();

	@GetMapping("/my-tenants")
	List<TenantResponseDto> getMyTenants();

	@GetMapping("/{tenantCustomId}")
	TenantResponseDto getTenantByCustomId(@PathVariable("tenantCustomId") String tenantCustomId);

	@PostMapping("/feedback")
	FeedbackResponseDto submitFeedback(@RequestBody FeedbackRequestDto dto);

	@GetMapping("/{tenantCustomId}/feedback")
	List<FeedbackResponseDto> getFeedbackHistory(@PathVariable("tenantCustomId") String tenantCustomId);
}
package com.bff.vikas.feign.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bff.vikas.config.FeignConfig;
import com.bff.vikas.feign.dto.request.ActiveAllocationDetails;
import com.bff.vikas.feign.dto.request.AllocationRequestDto;
import com.bff.vikas.feign.dto.request.PropertyAllocationResponseDto;
import com.bff.vikas.feign.dto.request.TenantRegistrationDto;
import com.bff.vikas.feign.dto.request.TenantResponseDto;
import com.bff.vikas.feign.dto.request.VacateRequestDto;

/**
 * Class      : TenantsServiceFeignClient
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 17, 2026
 * Version    : 1.0
 */
@FeignClient(name = "TENANTS-SERVICE",configuration = FeignConfig.class)
public interface TenantsServiceFeignClient {

	@GetMapping("/tenants/verify/{contact}")
	TenantResponseDto verifyTenant(@PathVariable("contact") String contact);

	@PostMapping("/tenants/register")
	TenantResponseDto registerTenant(@RequestBody TenantRegistrationDto dto);

	@PostMapping("/tenants/allot")
	Map<String, Object> allocateRoomOrBed(@RequestBody AllocationRequestDto dto);

	@PostMapping("/tenants/vacate")
	Map<String, Object> vacateRoomOrBed(@RequestBody VacateRequestDto dto);

	@GetMapping("/tenants/properties/{propertyId}/allocations")
	PropertyAllocationResponseDto getHistoryOfTenants(@PathVariable("propertyId") String propertyId);
	
	@PostMapping("/tenants/active-info")
    ActiveAllocationDetails getActiveAllocationFromTenantService(@RequestBody VacateRequestDto dto);
}

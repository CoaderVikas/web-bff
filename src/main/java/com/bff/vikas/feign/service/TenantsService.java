package com.bff.vikas.feign.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.vikas.feign.client.TenantsServiceFeignClient;
import com.bff.vikas.feign.dto.request.ActiveAllocationDetails;
import com.bff.vikas.feign.dto.request.AllocationRequestDto;
import com.bff.vikas.feign.dto.request.PropertyAllocationResponseDto;
import com.bff.vikas.feign.dto.request.TenantRegistrationDto;
import com.bff.vikas.feign.dto.request.TenantResponseDto;
import com.bff.vikas.feign.dto.request.VacateRequestDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Class      : TenantsService
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 17, 2026
 * Version    : 1.0
 */

@Service
@Slf4j
public class TenantsService {

	@Autowired
	private TenantsServiceFeignClient feignClient;

	public TenantResponseDto verifyTenant(String contact) {
		log.info("Calling Tenant Service: verifyTenant | contact={}", contact);
		return feignClient.verifyTenant(contact);
	}

	public TenantResponseDto registerTenant(TenantRegistrationDto dto) {
		log.info("Calling Tenant Service: registerTenant");
		return feignClient.registerTenant(dto);
	}

	public Map<String, Object> allocateRoomOrBed(AllocationRequestDto dto) {
		log.info("Calling Tenant Service: allocateRoomOrBed");
		return feignClient.allocateRoomOrBed(dto);
	}

	public Map<String, Object> vacateRoomOrBed(VacateRequestDto dto) {
		log.info("Calling Tenant Service: vacateRoomOrBed");
		return feignClient.vacateRoomOrBed(dto);
	}

	public PropertyAllocationResponseDto getHistoryOfTenants(String propertyId) {
		log.info("Calling Tenant Service: getHistoryOfTenants | propertyId={}", propertyId);
		return feignClient.getHistoryOfTenants(propertyId);
	}
	
	public ActiveAllocationDetails getActiveAllocationBypropertyInfo(VacateRequestDto dto) {
		log.info("Calling Tenant Service: getActiveAllocationBypropertyInfo | propertyId={}, room={}", 
				dto.getPropertyId(), dto.getRoomNumber());
		return feignClient.getActiveAllocationFromTenantService(dto);
	}
}
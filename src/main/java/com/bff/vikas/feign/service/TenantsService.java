package com.bff.vikas.feign.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bff.vikas.feign.client.TenantsServiceFeignClient;
import com.bff.vikas.feign.dto.request.ActiveAllocationDetails;
import com.bff.vikas.feign.dto.request.AllocationRequestDto;
import com.bff.vikas.feign.dto.request.FeedbackRequestDto;
import com.bff.vikas.feign.dto.request.PropertyAllocationResponseDto;
import com.bff.vikas.feign.dto.request.TenantRegistrationDto;
import com.bff.vikas.feign.dto.request.TenantResponseDto;
import com.bff.vikas.feign.dto.request.TenantUpdateRequestDto;
import com.bff.vikas.feign.dto.request.VacateRequestDto;
import com.bff.vikas.feign.dto.response.FeedbackResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class       : TenantsService
 * Description : Service wrapper to communicate with Tenant Microservice via Feign.
 * Author      : Vikas Yadav
 * Created On  : Jun 17, 2026
 * Version     : 1.2
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TenantsService {

	private final TenantsServiceFeignClient feignClient;

	public TenantResponseDto registerTenantSelf(TenantRegistrationDto dto) {
		log.info("Calling Tenant Service: registerTenantSelf");
		return feignClient.registerTenantSelf(dto);
	}

	public TenantResponseDto registerTenantByOwner(TenantRegistrationDto dto) {
		log.info("Calling Tenant Service: registerTenantByOwner");
		return feignClient.registerTenantByOwner(dto);
	}

	public TenantResponseDto verifyTenant(String input) {
		log.info("Calling Tenant Service: verifyTenant | input={}", input);
		return feignClient.verifyTenant(input);
	}

	public Map<String, Object> allocateRoomOrBed(AllocationRequestDto dto) {
		log.info("Calling Tenant Service: allocateRoomOrBed");
		return feignClient.allocateRoomOrBed(dto);
	}

	public Map<String, Object> vacateRoomOrBed(VacateRequestDto dto) {
		log.info("Calling Tenant Service: vacateRoomOrBed");
		return feignClient.vacateRoomOrBed(dto);
	}

	public PropertyAllocationResponseDto getPropertyAllocations(String propertyId) {
		log.info("Calling Tenant Service: getPropertyAllocations | propertyId={}", propertyId);
		return feignClient.getHistoryOfTenants(propertyId);
	}

	public List<ActiveAllocationDetails> getActiveAllocation(VacateRequestDto dto) {
		log.info("Calling Tenant Service: getActiveAllocation");
		return feignClient.getActiveAllocation(dto);
	}

	public TenantResponseDto updateTenantData(String customId, TenantUpdateRequestDto dto) {
		log.info("Calling Tenant Service: updateTenantData | customId={}", customId);
		return feignClient.updateTenantDataOnly(customId, dto);
	}


	public Resource getTenantImage(String customId) {
		log.info("Calling Tenant Service: getTenantImage | customId={}", customId);
		return feignClient.getTenantImage(customId);
	}

	public List<TenantResponseDto> getAllTenantsForAdmin() {
		log.info("Calling Tenant Service: getAllTenantsForAdmin");
		return feignClient.getAllTenantsForAdmin();
	}

	public List<TenantResponseDto> getMyTenants() {
		log.info("Calling Tenant Service: getMyTenants");
		return feignClient.getMyTenants();
	}

	public TenantResponseDto getTenantByCustomId(String tenantCustomId) {
		log.info("Calling Tenant Service: getTenantByCustomId | id={}", tenantCustomId);
		return feignClient.getTenantByCustomId(tenantCustomId);
	}

	public FeedbackResponseDto submitFeedback(FeedbackRequestDto dto) {
		log.info("Calling Tenant Service: submitFeedback");
		return feignClient.submitFeedback(dto);
	}

	public List<FeedbackResponseDto> getFeedbackHistory(String tenantCustomId) {
		log.info("Calling Tenant Service: getFeedbackHistory | id={}", tenantCustomId);
		return feignClient.getFeedbackHistory(tenantCustomId);
	}
	
	public TenantResponseDto updateTenantImage(String customId, MultipartFile file) {
		log.info("Calling Tenant Service: updateTenantImage | customId={}", customId);
		return feignClient.updateImageProfile(customId, file);
	}

}
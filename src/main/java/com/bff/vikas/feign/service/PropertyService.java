package com.bff.vikas.feign.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bff.vikas.feign.client.PropertyServiceFeignClient;
import com.bff.vikas.feign.dto.request.PropertyRequestDTO;
import com.bff.vikas.feign.dto.request.PropertyUpdateRequestDTO;
import com.bff.vikas.feign.dto.response.PropertyResponseDTO;
import com.bff.vikas.feign.fallback.PropertyFallbackHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PropertyService {

	@Autowired
	private PropertyServiceFeignClient feignClient;

	@Autowired
	private PropertyFallbackHandler fallbackHandler;

	@Autowired
	private ObjectMapper objectMapper;

	private static final String PROPERTY_SERVICE = "propertyService";

	public PropertyResponseDTO createProperty(PropertyRequestDTO request) {
		log.info("Calling Property Service: createProperty");
		return feignClient.createProperty(request).getBody();
	}

	/**
	 * Multipart create — images ke saath.
	 */
	public PropertyResponseDTO createPropertyWithImages(PropertyRequestDTO request, List<MultipartFile> images) {
		log.info("Calling Property Service: createPropertyWithImages | imageCount={}",
				images == null ? 0 : images.size());
		try {
			String propertyJson = objectMapper.writeValueAsString(request);
			return feignClient.createPropertyWithImages(propertyJson, images).getBody();
		} catch (Exception e) {
			log.error("Failed to serialize property request", e);
			throw new RuntimeException("Failed to process property request", e);
		}
	}

	public PropertyResponseDTO getProperty(String id) {
		log.info("Calling Property Service: getProperty | id={}", id);
		return feignClient.getPropertyById(id).getBody();
	}

	public PropertyResponseDTO updateProperty(String id, PropertyRequestDTO request) {
		log.info("Calling Property Service: updateProperty | id={}", id);
		return feignClient.updateProperty(id, request).getBody();
	}

	public String deleteProperty(String id) {
		log.info("Calling Property Service: deleteProperty | id={}", id);
		return feignClient.deleteProperty(id).getBody();
	}

	public List<PropertyResponseDTO> getMyProperties() {
		return feignClient.getMyProperties();
	}

	public Long getTotalProperties(String token) {
		log.info("Calling Property Service: getTotalProperties");
		return feignClient.getTotalProperties(token);
	}

	public Boolean checkPropertyExists(String id) {
		log.info("Calling Property Service: checkPropertyExists | id={}", id);
		return feignClient.checkPropertyExists(id).getBody();
	}

	public ResponseEntity<?> syncPropertyOccupancy(String id, PropertyUpdateRequestDTO request) {
		log.info("Calling Property Service: syncPropertyOccupancy | id={}", id);
		return feignClient.syncPropertyOccupancy(id, request);
	}

	public PropertyResponseDTO createPropertyFallback(PropertyRequestDTO req, Throwable e) {
		return fallbackHandler.propertyFallback(e);
	}

	public PropertyResponseDTO getPropertyFallback(String id, Throwable e) {
		return fallbackHandler.propertyFallback(e);
	}

	public PropertyResponseDTO updatePropertyFallback(String id, PropertyRequestDTO req, Throwable e) {
		return fallbackHandler.propertyFallback(e);
	}

	public String deletePropertyFallback(String id, Throwable e) {
		return fallbackHandler.stringFallback(e);
	}
}
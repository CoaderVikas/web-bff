package com.bff.vikas.feign.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bff.vikas.feign.client.PropertyServiceFeignClient;
import com.bff.vikas.feign.dto.request.PropertyRequestDTO;
import com.bff.vikas.feign.dto.response.PropertyResponseDTO;
import com.bff.vikas.feign.fallback.PropertyFallbackHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Class      : PropertyService
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 29, 2026
 * Version    : 1.0
 */

@Service
@Slf4j
public class PropertyService {

	@Autowired
	private PropertyServiceFeignClient feignClient;

	@Autowired
	private PropertyFallbackHandler fallbackHandler;

	private static final String PROPERTY_SERVICE = "propertyService";

	/**
	 * 
	 * @param request
	 * @return
	 */
	//@Retry(name = PROPERTY_SERVICE)
	//@CircuitBreaker(name = PROPERTY_SERVICE, fallbackMethod = "createPropertyFallback")
	public PropertyResponseDTO createProperty(PropertyRequestDTO request) {
		log.info("Calling Property Service: createProperty");
		return feignClient.createProperty(request).getBody();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	//@Retry(name = PROPERTY_SERVICE)
	//@CircuitBreaker(name = PROPERTY_SERVICE, fallbackMethod = "getPropertyFallback")
	public PropertyResponseDTO getProperty(String id) {
		log.info("Calling Property Service: getProperty | id={}", id);
		return feignClient.getPropertyById(id).getBody();
	}

	
	//@Retry(name = PROPERTY_SERVICE)
	//@CircuitBreaker(name = PROPERTY_SERVICE, fallbackMethod = "updatePropertyFallback")
	public PropertyResponseDTO updateProperty(String  id, PropertyRequestDTO request) {
		log.info("Calling Property Service: updateProperty | id={}", id);
		return feignClient.updateProperty(id, request).getBody();
	}

	/**
	 * -
	 * @param id
	 * @return
	 */
	//@Retry(name = PROPERTY_SERVICE)
	//@CircuitBreaker(name = PROPERTY_SERVICE, fallbackMethod = "deletePropertyFallback")
	public String deleteProperty(String id) {
		log.info("Calling Property Service: deleteProperty | id={}", id);
		return feignClient.deleteProperty(id).getBody();
	}
	/**
	 * 
	 * @return
	 */
	public List<PropertyResponseDTO> getMyProperties() {
	    return feignClient.getMyProperties();
	}
	
	public String uploadPropertyImage(UUID id, MultipartFile file) {
		log.info("Calling Property Service: uploadPropertyImage | id={}", id);
		return feignClient.uploadPropertyImage(id, file);
	}

	public Long getTotalProperties(String token) {
		log.info("Calling Property Service: getTotalProperties");
		return feignClient.getTotalProperties(token);
	}

	public PropertyResponseDTO createPropertyFallback(PropertyRequestDTO req, Throwable e) {
		return fallbackHandler.propertyFallback(e);
	}

	public PropertyResponseDTO getPropertyFallback(UUID id, Throwable e) {
		return fallbackHandler.propertyFallback(e);
	}

	public PropertyResponseDTO updatePropertyFallback(UUID id, PropertyRequestDTO req, Throwable e) {
		return fallbackHandler.propertyFallback(e);
	}

	public String deletePropertyFallback(UUID id, Throwable e) {
		return fallbackHandler.stringFallback(e);
	}
}

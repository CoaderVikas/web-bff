package com.bff.vikas.feign.fallback;

import org.springframework.stereotype.Component;

import com.bff.vikas.feign.dto.response.PropertyResponseDTO;

/**
 * Class      : PropertyFallbackHandler
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 29, 2026
 * Version    : 1.0
 */

@Component
public class PropertyFallbackHandler {

	public PropertyResponseDTO propertyFallback(Throwable e) {
		return new PropertyResponseDTO(); // return safe default
	}

	public String stringFallback(Throwable e) {
		return "Service unavailable. Please try again later.";
	}
}

package com.bff.vikas.feign.fallback;

import org.springframework.stereotype.Component;

import com.bff.vikas.feign.dto.response.PropertyPageResponse;
import com.bff.vikas.feign.dto.response.PropertyResponse;

/**
 * Class      : PropertyFallbackHandler
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 29, 2026
 * Version    : 1.0
 */

@Component
public class PropertyFallbackHandler {

    public PropertyResponse propertyFallback(Throwable e) {
        return new PropertyResponse(); // return safe default
    }

    public PropertyPageResponse propertyPageFallback(Throwable e) {
        return new PropertyPageResponse(); // empty response
    }

    public String stringFallback(Throwable e) {
        return "Service unavailable. Please try again later.";
    }
}

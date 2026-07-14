package com.bff.vikas.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Class      : FeignRequestIdInterceptor
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jul 13, 2026
 * Version    : 1.0
 */

@Component
public class FeignRequestIdInterceptor implements RequestInterceptor {
	@Override
	public void apply(RequestTemplate template) {
		// Incoming request ke header se requestId nikalo
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attrs != null) {
			String requestId = attrs.getRequest().getHeader("X-Request-Id");
			if (requestId != null) {
				template.header("X-Request-Id", requestId);
			}
		}
	}
}
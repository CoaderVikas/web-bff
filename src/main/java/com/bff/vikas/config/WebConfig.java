package com.bff.vikas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class      : WebConfig
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 17, 2026
 * Version    : 1.0
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix("/rent-hub/api/v1", c -> c.isAnnotationPresent(RestController.class));
	}
}
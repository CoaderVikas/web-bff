package com.bff.vikas.config;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.bff.vikas.exception.BffServiceException;
import com.bff.vikas.feign.dto.ApiError;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

/**
 * Class : FeignErrorDecoder 
 * Description: [Add brief description here] 
 * Author :Vikas Yadav 
 * Created On : Apr 20, 2026 
 * Version : 1.0
 */
@Component
public class FeignErrorDecoder implements ErrorDecoder {
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Exception decode(String methodKey, Response response) {
		String originalBody = "";
		try {
			originalBody = Util.toString(response.body().asReader(StandardCharsets.UTF_8));

			if (originalBody.startsWith("\"") && originalBody.endsWith("\"")) {
				originalBody = originalBody.substring(1, originalBody.length() - 1).replace("\\\"", "\"");
			}

			ApiError apiError = mapper.readValue(originalBody, ApiError.class);

			if (apiError.getStatus() <= 0)
				apiError.setStatus(response.status());
			if (apiError.getTimestamp() == null)
				apiError.setTimestamp(LocalDateTime.now().toString());

			return new BffServiceException(apiError);

		} catch (Exception e) {
			String cleanMessage = extractMessage(originalBody);

			ApiError fallback = ApiError.builder().message(cleanMessage).status(response.status())
					.timestamp(LocalDateTime.now().toString()).build();

			return new BffServiceException(fallback);
		}
	}

	private String extractMessage(String body) {
		try {
			JsonNode node = mapper.readTree(body);
			if (node.has("message")) {
				return node.get("message").asText();
			}
		} catch (Exception ignored) {
		}
		return body;
	}
}
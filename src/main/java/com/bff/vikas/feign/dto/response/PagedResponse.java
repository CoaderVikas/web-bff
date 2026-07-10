package com.bff.vikas.feign.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class       : PagedResponse
 * Description : Lightweight, dependency-free page wrapper for the BFF layer.
 *               Matches the STABLE JSON shape produced by Spring Data's
 *               VIA_DTO page serialization mode on the property-service:
 *
 *               {
 *                 "content": [ ...items... ],
 *                 "page": { "size": 20, "number": 0, "totalElements": 42, "totalPages": 3 }
 *               }
 *
 *               Feign deserializes into this instead of Spring's PageImpl (which is
 *               not safely deserializable). Unknown keys are ignored for forward-compat.
 *
 * Author      : Vikas Yadav
 * Created On  : Jul 10, 2026
 * Version     : 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagedResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The actual page of items. */
	@Builder.Default
	private List<T> content = new ArrayList<>();

	/** Pagination metadata block (matches VIA_DTO's nested "page" object). */
	@Builder.Default
	private PageMetadata page = new PageMetadata();

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class PageMetadata implements Serializable {

		private static final long serialVersionUID = 1L;

		/** Page size (items per page). */
		@JsonProperty("size")
		private int size;

		/** Current page number (0-based). */
		@JsonProperty("number")
		private int number;

		/** Total items across all pages. */
		@JsonProperty("totalElements")
		private long totalElements;

		/** Total number of pages. */
		@JsonProperty("totalPages")
		private int totalPages;
	}
}
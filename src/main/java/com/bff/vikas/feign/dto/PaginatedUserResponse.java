package com.bff.vikas.feign.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : PaginatedUserResponse
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 21, 2026
 * Version    : 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginatedUserResponse {
	private List<AdminUserResponse> users;
	private long totalElements;
	private int totalPages;
	private int currentPage;
	private boolean isLast;
}

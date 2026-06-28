package com.bff.vikas.feign.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.bff.vikas.enums.AgreementStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : AgreementResponseDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 24, 2026
 * Version    : 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgreementResponseDto {
	private String agreementId;
	private String tenantId;
	private String propertyId;
	private String unitId;
	private String ownerId;
	private BigDecimal rentAmount;
	private BigDecimal securityDeposit;
	private BigDecimal advanceAmount;
	private Integer dueDayOfMonth;
	private LocalDate leaseStartDate;
	private LocalDate leaseEndDate;
	private AgreementStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String message;
}
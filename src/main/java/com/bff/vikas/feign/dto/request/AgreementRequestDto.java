package com.bff.vikas.feign.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : AgreementRequestDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 24, 2026
 * Version    : 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgreementRequestDto {
	private String tenantId;
	private String propertyId;
	private String unitId;
	private String ownerId;
	private String paymentId;
	private String paymentReferenceNumber;
	private BigDecimal rentAmount;
	private BigDecimal securityDeposit;
	private BigDecimal advanceAmount;
	private Integer dueDayOfMonth;
	private LocalDate leaseStartDate;
	private LocalDate leaseEndDate;
}
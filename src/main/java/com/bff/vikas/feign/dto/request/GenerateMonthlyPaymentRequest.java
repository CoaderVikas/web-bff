package com.bff.vikas.feign.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : GenerateMonthlyPaymentRequest
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 24, 2026
 * Version    : 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateMonthlyPaymentRequest {
	private Long tenantId;
	private Long propertyId;
	private Long unitId;
	private BigDecimal rentAmount;
	private LocalDate leaseStartDate;
	private LocalDate leaseEndDate;
	private Integer dueDayOfMonth;
}

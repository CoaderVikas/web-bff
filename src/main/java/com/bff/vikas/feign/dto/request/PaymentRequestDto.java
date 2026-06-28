package com.bff.vikas.feign.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bff.vikas.enums.PaymentMode;
import com.bff.vikas.enums.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : PaymentRequestDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 24, 2026
 * Version    : 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {
	private String tenantId;
	private String propertyId;
	private String unitId;
	private PaymentType paymentType;
	private String month;
	private BigDecimal amount;
	private LocalDate dueDate;
	private PaymentMode paymentMode;
	private String externalReference;
	private String collectedBy;
	private LocalDate leaseStartDate;
	private LocalDate leaseEndDate;
}
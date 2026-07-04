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
	// private BigDecimal advanceAmount;
	// private String paymentFor;
	private BigDecimal amount;
	private Integer bedSerialNo;
	private String collectedBy;
	private LocalDate dueDate;
	private Integer dueDayOfMonth;
	private LocalDate leaseEndDate;
	private LocalDate leaseStartDate;
	private String month;
	private LocalDate paymentDate;//
	private PaymentMode paymentMode;
	private String paymentRequestId;
	private PaymentType paymentType;
	private String propertyId;
	private BigDecimal rentAmount;
	private BigDecimal securityDeposit;
	private String tenantId;
	private String unitId;
}
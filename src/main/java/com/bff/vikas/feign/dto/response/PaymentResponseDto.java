package com.bff.vikas.feign.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.bff.vikas.enums.PaymentMode;
import com.bff.vikas.enums.PaymentStatus;
import com.bff.vikas.enums.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : PaymentResponseDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 24, 2026
 * Version    : 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {
	private String paymentId;
	private String tenantId;
	private String propertyId;
	private String unitId;
	private PaymentType paymentType;
	private String month;
	private BigDecimal amount;
	private LocalDate dueDate;
	private LocalDate paymentDate;
	private PaymentStatus status;
	private PaymentMode paymentMode;
	private String systemReference;
	private String externalReference;
	private String collectedBy;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
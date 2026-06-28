package com.bff.vikas.feign.dto.request;

import com.bff.vikas.enums.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class      : MarkPaidRequestDto
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 24, 2026
 * Version    : 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkPaidRequestDto {
	private PaymentMode paymentMode;
	private String externalReference;
	private String collectedBy;
}

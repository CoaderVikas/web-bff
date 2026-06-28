package com.bff.vikas.feign.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.vikas.feign.client.PaymentFeignClient;
import com.bff.vikas.feign.dto.request.MarkPaidRequestDto;
import com.bff.vikas.feign.dto.request.PaymentRequestDto;
import com.bff.vikas.feign.dto.response.PaymentResponseDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Class      : PaymentService
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 24, 2026
 * Version    : 1.0
 */
@Service
@Slf4j
public class PaymentService {

	@Autowired
	private PaymentFeignClient paymentFeignClient;

	public PaymentResponseDto recordPayment(PaymentRequestDto request) {
		log.info("BFF: Recording payment for tenantId={}", request.getTenantId());
		return paymentFeignClient.recordPayment(request);
	}

	public PaymentResponseDto markAsPaid(Long paymentId, MarkPaidRequestDto request) {
		log.info("BFF: Marking payment as paid, paymentId={}", paymentId);
		return paymentFeignClient.markAsPaid(paymentId, request);
	}

	public List<PaymentResponseDto> getPaymentsByOwner(String ownerId) {
		log.info("BFF: Fetching payments for ownerId={}", ownerId);
		return paymentFeignClient.getPaymentsByOwner();
	}

	public List<PaymentResponseDto> getPaymentsByTenant(String tenantId) {
		log.info("BFF: Fetching payments for tenantId={}", tenantId);
		return paymentFeignClient.getPaymentsByTenant(tenantId);
	}

	public List<PaymentResponseDto> getPaymentsByProperty(String propertyId) {
		log.info("BFF: Fetching payments for propertyId={}", propertyId);
		return paymentFeignClient.getPaymentsByProperty(propertyId);
	}

	public List<PaymentResponseDto> getPendingPayments() {
		log.info("BFF: Fetching all pending payments");
		return paymentFeignClient.getPendingPayments();
	}

	public List<PaymentResponseDto> getOverduePayments() {
		log.info("BFF: Fetching all overdue payments");
		return paymentFeignClient.getOverduePayments();
	}

}

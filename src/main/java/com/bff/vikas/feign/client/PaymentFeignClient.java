package com.bff.vikas.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bff.vikas.config.FeignConfig;
import com.bff.vikas.feign.dto.request.GenerateMonthlyPaymentRequest;
import com.bff.vikas.feign.dto.request.MarkPaidRequestDto;
import com.bff.vikas.feign.dto.request.PaymentRequestDto;
import com.bff.vikas.feign.dto.response.PaymentResponseDto;

/**
 * Class      : PaymentFeignClient
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 24, 2026
 * Version    : 1.0
 */
@FeignClient(name = "payment-service",configuration = FeignConfig.class)
public interface PaymentFeignClient {
	@PostMapping("/payment/record")
	PaymentResponseDto recordPayment(@RequestBody PaymentRequestDto request);

	@PostMapping("/payment/generate-monthly")
	void generateMonthlyRentEntries(@RequestBody GenerateMonthlyPaymentRequest request);

	@PutMapping("/payment/{paymentId}/mark-paid")
	PaymentResponseDto markAsPaid(@PathVariable("paymentId") Long paymentId, @RequestBody MarkPaidRequestDto request);

	@GetMapping("/payment/owner")
	List<PaymentResponseDto> getPaymentsByOwner();

	@GetMapping("/payment/tenant/{tenantId}")
	List<PaymentResponseDto> getPaymentsByTenant(@PathVariable("tenantId") String tenantId);

	@GetMapping("/payment/property/{propertyId}")
	List<PaymentResponseDto> getPaymentsByProperty(@PathVariable("propertyId") String propertyId);

	@GetMapping("/payment/pending")
	List<PaymentResponseDto> getPendingPayments();

	@GetMapping("/payment/overdue")
	List<PaymentResponseDto> getOverduePayments();
}

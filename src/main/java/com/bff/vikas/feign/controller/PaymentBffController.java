package com.bff.vikas.feign.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bff.vikas.feign.dto.request.MarkPaidRequestDto;
import com.bff.vikas.feign.dto.request.PaymentRequestDto;
import com.bff.vikas.feign.dto.response.PaymentResponseDto;
import com.bff.vikas.feign.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class      : PaymentBffController
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jun 24, 2026
 * Version    : 1.0
 */

@RestController
@RequestMapping(value = "/payment")
@AllArgsConstructor
@Slf4j
@Tag(name = "Payment Management BFF", description = "Endpoints for Payment operations")
public class PaymentBffController {

	private final PaymentService paymentService;

	@Operation(summary = "Record Initial Payment (Advance/Security)")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Payment Recorded"),
			@ApiResponse(responseCode = "400", description = "Bad Request") })
	@PostMapping("/record")
	public ResponseEntity<PaymentResponseDto> recordPayment(@Valid @RequestBody PaymentRequestDto request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.recordPayment(request));
	}

	@Operation(summary = "Mark Payment as Paid")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Marked as Paid"),
			@ApiResponse(responseCode = "404", description = "Payment Not Found") })
	@PutMapping("/{paymentId}/mark-paid")
	public ResponseEntity<PaymentResponseDto> markAsPaid(@PathVariable("paymentId") Long paymentId,
			@Valid @RequestBody MarkPaidRequestDto request) {
		return ResponseEntity.ok(paymentService.markAsPaid(paymentId, request));
	}

	@Operation(summary = "Get Payments by Owner")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No Payments Found") })
	@GetMapping("/owner/{ownerId}")
	public ResponseEntity<List<PaymentResponseDto>> getByOwner(@PathVariable("ownerId") String ownerId) {
		List<PaymentResponseDto> list = paymentService.getPaymentsByOwner(ownerId);
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}

	@Operation(summary = "Get Payments by Tenant")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No Payments Found") })
	@GetMapping("/tenant/{tenantId}")
	public ResponseEntity<List<PaymentResponseDto>> getByTenant(@PathVariable("tenantId") String tenantId) {
		List<PaymentResponseDto> list = paymentService.getPaymentsByTenant(tenantId);
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}

	@Operation(summary = "Get Payments by Property")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No Payments Found") })
	@GetMapping("/property/{propertyId}")
	public ResponseEntity<List<PaymentResponseDto>> getByProperty(@PathVariable("propertyId") String propertyId) {
		List<PaymentResponseDto> list = paymentService.getPaymentsByProperty(propertyId);
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}

	@Operation(summary = "Get All Pending Payments")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No Pending Payments") })
	@GetMapping("/pending")
	public ResponseEntity<List<PaymentResponseDto>> getPending() {
		List<PaymentResponseDto> list = paymentService.getPendingPayments();
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}

	@Operation(summary = "Get All Overdue Payments")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "204", description = "No Overdue Payments") })
	@GetMapping("/overdue")
	public ResponseEntity<List<PaymentResponseDto>> getOverdue() {
		List<PaymentResponseDto> list = paymentService.getOverduePayments();
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}
}

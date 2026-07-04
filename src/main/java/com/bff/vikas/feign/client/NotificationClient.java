package com.bff.vikas.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bff.vikas.config.FeignConfig;
import com.bff.vikas.feign.dto.request.CreateBookingNotificationRequest;
import com.bff.vikas.feign.dto.request.CreateChatNotificationRequest;
import com.bff.vikas.feign.dto.request.SendMessageRequest;
import com.bff.vikas.feign.dto.response.ChatMessageResponseDto;
import com.bff.vikas.feign.dto.response.NotificationResponseDto;

/**
 * Class      : NotificationClient
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jul 4, 2026
 * Version    : 1.0
 */
@FeignClient(name = "notification-service", path = "/notifications",configuration = FeignConfig.class)
public interface NotificationClient {

	@PostMapping("/booking")
	NotificationResponseDto createBooking(@RequestBody CreateBookingNotificationRequest request);

	@PostMapping("/chat")
	NotificationResponseDto createChat(@RequestBody CreateChatNotificationRequest request);

	@GetMapping
	List<NotificationResponseDto> getOwnerNotifications();

	@PostMapping("/{id}/accept-booking")
	NotificationResponseDto acceptBooking(@PathVariable("id") Long id);

	@PostMapping("/{id}/reject-booking")
	void rejectBooking(@PathVariable("id") Long id);

	@PostMapping("/{id}/accept-chat")
	NotificationResponseDto acceptChat(@PathVariable("id") Long id);

	@GetMapping("/chats/{id}/messages")
	List<ChatMessageResponseDto> getChatMessages(@PathVariable("id") Long id);

	@PostMapping("/chats/{id}/messages")
	ChatMessageResponseDto sendChatMessage(@PathVariable("id") Long id, @RequestBody SendMessageRequest request);

	@PostMapping("/chats/{id}/complete")
	void completeChat(@PathVariable("id") Long id);
	
	@GetMapping("/tenant")
	List<NotificationResponseDto> getTenantNotifications();
}

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

import com.bff.vikas.feign.client.NotificationClient;
import com.bff.vikas.feign.dto.request.CreateBookingNotificationRequest;
import com.bff.vikas.feign.dto.request.CreateChatNotificationRequest;
import com.bff.vikas.feign.dto.request.SendMessageRequest;
import com.bff.vikas.feign.dto.response.ChatMessageResponseDto;
import com.bff.vikas.feign.dto.response.NotificationResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class       : NotificationBffController
 * Description : BFF pass-through for Notification + Chat + read-state operations.
 * Author      : Vikas Yadav
 * Created On  : Jul 4, 2026
 * Version     : 1.1 (read-state endpoints added)
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/notifications")
@Tag(name = "Notification Management BFF", description = "Endpoints for Notification and Chat operations")
public class NotificationBffController {

    private final NotificationClient notificationClient;

    // ── Creation Endpoints ──

    @Operation(summary = "Create Booking Notification")
    @PostMapping("/booking")
    public ResponseEntity<NotificationResponseDto> createBooking(
            @RequestBody CreateBookingNotificationRequest request) {
        log.info("BFF: Creating booking notification");
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationClient.createBooking(request));
    }

    @Operation(summary = "Create Chat Notification")
    @PostMapping("/chat")
    public ResponseEntity<NotificationResponseDto> createChat(@RequestBody CreateChatNotificationRequest request) {
        log.info("BFF: Creating chat notification");
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationClient.createChat(request));
    }

    // ── Owner Side Endpoints ──

    @Operation(summary = "Get Owner Notifications")
    @GetMapping
    public ResponseEntity<List<NotificationResponseDto>> getOwnerNotifications() {
        log.info("BFF: Fetching notifications for owner");
        List<NotificationResponseDto> list = notificationClient.getOwnerNotifications();
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    // ── Read State Endpoints ──
    // Bell dropdown me notification dekh lene par read mark karne ke liye.
    // List persist rehti hai; sirf unread badge count ghatta hai.

    @Operation(summary = "Mark a notification as read")
    @PutMapping("/{id}/read")
    public ResponseEntity<NotificationResponseDto> markAsRead(@PathVariable("id") Long id) {
        log.info("BFF: Marking notification read id={}", id);
        return ResponseEntity.ok(notificationClient.markAsRead(id));
    }

    @Operation(summary = "Mark all notifications as read")
    @PutMapping("/read-all")
    public ResponseEntity<Void> markAllRead() {
        log.info("BFF: Marking all notifications read for current user");
        notificationClient.markAllRead();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Accept Booking")
    @PostMapping("/{id}/accept-booking")
    public ResponseEntity<NotificationResponseDto> acceptBooking(@PathVariable("id") Long id) {
        log.info("BFF: Accepting booking id={}", id);
        return ResponseEntity.ok(notificationClient.acceptBooking(id));
    }

    @Operation(summary = "Reject Booking")
    @PostMapping("/{id}/reject-booking")
    public ResponseEntity<Void> rejectBooking(@PathVariable("id") Long id) {
        log.info("BFF: Rejecting booking id={}", id);
        notificationClient.rejectBooking(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Accept Chat")
    @PostMapping("/{id}/accept-chat")
    public ResponseEntity<NotificationResponseDto> acceptChat(@PathVariable("id") Long id) {
        log.info("BFF: Accepting chat id={}", id);
        return ResponseEntity.ok(notificationClient.acceptChat(id));
    }

    // ── Chat Endpoints ──

    @Operation(summary = "Get Chat Messages")
    @GetMapping("/chats/{id}/messages")
    public ResponseEntity<List<ChatMessageResponseDto>> getChatMessages(@PathVariable("id") Long id) {
        log.info("BFF: Fetching messages for chat id={}", id);
        return ResponseEntity.ok(notificationClient.getChatMessages(id));
    }

    @Operation(summary = "Send Chat Message")
    @PostMapping("/chats/{id}/messages")
    public ResponseEntity<ChatMessageResponseDto> sendChatMessage(@PathVariable("id") Long id,
            @RequestBody SendMessageRequest request) {
        log.info("BFF: Sending message to chat id={}", id);
        return ResponseEntity.ok(notificationClient.sendChatMessage(id, request));
    }

    @Operation(summary = "Complete Chat")
    @PostMapping("/chats/{id}/complete")
    public ResponseEntity<Void> completeChat(@PathVariable("id") Long id) {
        log.info("BFF: Completing chat id={}", id);
        notificationClient.completeChat(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get Tenant Notifications")
    @GetMapping("/tenant")
    public ResponseEntity<List<NotificationResponseDto>> getTenantNotifications() {
        log.info("BFF: Fetching notifications for tenant");
        List<NotificationResponseDto> list = notificationClient.getTenantNotifications();
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }
}

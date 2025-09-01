package com.example.grocery_be.controllers;

import com.example.grocery_be.dtos.PaymentRequest;
import com.example.grocery_be.dtos.PaymentResponse;
import com.example.grocery_be.enities.Order;
import com.example.grocery_be.enities.User;
import com.example.grocery_be.services.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import vn.payos.type.PaymentLinkData;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(
            @RequestBody PaymentRequest req,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.createPayment(req, user.getId()));
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<PaymentLinkData> getOrder(@PathVariable long orderCode) {
        return ResponseEntity.ok(orderService.getOrderFromPayOS(orderCode));
    }

    @PutMapping("/{orderCode}/cancel")
    public ResponseEntity<PaymentLinkData> cancelOrder(@PathVariable long orderCode) {
        return ResponseEntity.ok(orderService.cancelOrder(orderCode));
    }

    // Confirm sau khi user thanh toán (FE gọi)
    @PostMapping("/confirm")
    public ResponseEntity<Order> confirmPayment(
            @RequestParam String orderCode,
            @RequestParam boolean success) {
        return ResponseEntity.ok(orderService.confirmPayment(orderCode, success));
    }
}

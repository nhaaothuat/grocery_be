package com.example.grocery_be.services.order;

import com.example.grocery_be.dtos.PaymentRequest;
import com.example.grocery_be.dtos.PaymentResponse;
import com.example.grocery_be.enities.Order;
import vn.payos.type.PaymentLinkData;

public interface OrderService {
    PaymentResponse createPayment(PaymentRequest req, String userId);

    PaymentLinkData getOrderFromPayOS(long orderCode);

    PaymentLinkData cancelOrder(long orderCode);

    Order confirmPayment(String orderCode, boolean success);
}

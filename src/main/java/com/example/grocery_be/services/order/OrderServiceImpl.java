package com.example.grocery_be.services.order;

import com.example.grocery_be.dtos.PaymentRequest;
import com.example.grocery_be.dtos.PaymentResponse;
import com.example.grocery_be.enities.Order;
import com.example.grocery_be.enums.OrderStatus;
import com.example.grocery_be.enums.PaymentMethod;
import com.example.grocery_be.mappers.OrderMapper;
import com.example.grocery_be.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.payos.PayOS;
import vn.payos.type.CheckoutResponseData;
import vn.payos.type.ItemData;
import vn.payos.type.PaymentData;
import vn.payos.type.PaymentLinkData;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final PayOS payOS;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public PaymentResponse createPayment(PaymentRequest req, String userId) {
        try {
            // Tạo orderCode ngẫu nhiên (dựa theo timestamp)
            String currentTimeString = String.valueOf(new Date().getTime());
            long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6));

            // Tạo item (ở đây demo, thực tế bạn map từ CartItem)
            ItemData item = ItemData.builder()
                    .name(req.getDescription())
                    .price(req.getTotalPrice().intValue())
                    .quantity(1)
                    .build();

            PaymentData paymentData = PaymentData.builder()
                    .orderCode(orderCode)
                    .description(req.getDescription())
                    .amount(req.getTotalPrice().intValue())
                    .item(item)
                    .returnUrl(req.getReturnUrl())
                    .cancelUrl(req.getCancelUrl())
                    .build();

            CheckoutResponseData checkoutData = payOS.createPaymentLink(paymentData);

            // Lưu Order vào DB (status = PENDING)
            Order order = new Order();
            order.setUserId(userId);
            order.setOrderCode(String.valueOf(orderCode));
            order.setTotalPrice(req.getTotalPrice());
            order.setDescription(req.getDescription());
            order.setMethod(PaymentMethod.PAYOS);
            order.setStatus(OrderStatus.PENDING);
            order.setCreatedAt(LocalDateTime.now());
            order.setUpdatedAt(LocalDateTime.now());
            orderRepository.save(order);

            // Trả response
            PaymentResponse res = new PaymentResponse();
            res.setOrderCode(String.valueOf(orderCode));
            res.setDescription(req.getDescription());
            res.setData(checkoutData); // Có checkoutUrl để FE redirect
            return res;
        } catch (Exception e) {
            throw new RuntimeException("Tạo payment thất bại: " + e.getMessage());
        }
    }


    public PaymentLinkData getOrderFromPayOS(long orderCode) {
        try {
            return payOS.getPaymentLinkInformation(orderCode);
        } catch (Exception e) {
            throw new RuntimeException("Không lấy được thông tin order: " + e.getMessage());
        }
    }

        public PaymentLinkData cancelOrder(long orderCode) {
            try {
                return payOS.cancelPaymentLink(orderCode, null);
            } catch (Exception e) {
                throw new RuntimeException("Hủy order thất bại: " + e.getMessage());
            }
        }

        // Confirm thanh toán (không dùng webhook → client gọi returnUrl FE → FE gọi BE confirm)
        public Order confirmPayment(String orderCode, boolean success) {
            Order order = orderRepository.findByOrderCode(orderCode)
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            order.setStatus(success ? OrderStatus.PAID : OrderStatus.CANCELLED);
            order.setUpdatedAt(LocalDateTime.now());
            return orderRepository.save(order);
        }
}

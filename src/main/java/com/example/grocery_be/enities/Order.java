package com.example.grocery_be.enities;

import com.example.grocery_be.enums.OrderStatus;
import com.example.grocery_be.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String id;

    private String userId;         // Liên kết tới User
    private String orderCode;      // Mã đơn hàng (tạo ra để gửi PayOS)
    private Long totalPrice;           // Số tiền thanh toán
    private String description;    // Mô tả
    private PaymentMethod method;         // COD, PayOS
    private OrderStatus status;         // PENDING, SUCCESS, FAILED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

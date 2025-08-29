package com.example.grocery_be.enums;

public enum OrderStatus {
    PENDING,    // Chờ xử lý / chưa thanh toán
    PAID,       // Đã thanh toán (PayOS thành công)
    CANCELLED,  // Đã hủy
    DELIVERED   // Đã giao hàng
}

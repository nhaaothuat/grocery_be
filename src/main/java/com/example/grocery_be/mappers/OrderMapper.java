package com.example.grocery_be.mappers;

import com.example.grocery_be.dtos.PaymentRequest;
import com.example.grocery_be.dtos.PaymentResponse;
import com.example.grocery_be.enities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(PaymentRequest request);

    @Mapping(target = "data", ignore = true) // dữ liệu từ PayOS trả về
    PaymentResponse toPaymentResponse(Order order);
}

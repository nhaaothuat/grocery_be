package com.example.grocery_be.mappers;

import com.example.grocery_be.dtos.*;
import com.example.grocery_be.enities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUserRequestDTO(UserRequestDTO userRequestDTO);

    User toUserInRequestDTO(UserInRequestDTO userInRequestDTO);

    UserDTO toUserDTO(User user);


    @Mapping(target = "jwt ",source = "jwt")
    UserResponseDTO toUserResponseDTO(User user, String jwt);

    UserCartDTO toCart(User user);
}

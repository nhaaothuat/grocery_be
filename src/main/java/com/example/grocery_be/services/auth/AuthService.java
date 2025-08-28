package com.example.grocery_be.services.auth;

import com.example.grocery_be.dtos.UserDTO;
import com.example.grocery_be.dtos.UserInRequestDTO;
import com.example.grocery_be.dtos.UserRequestDTO;
import com.example.grocery_be.dtos.UserResponseDTO;

public interface AuthService {

    UserDTO signUp(UserRequestDTO userRequestDTO);

    UserResponseDTO signIn(UserInRequestDTO userInRequestDTO);

    Boolean hasUserWithEmail(String email);
}

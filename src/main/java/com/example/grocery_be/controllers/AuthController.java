package com.example.grocery_be.controllers;


import com.example.grocery_be.dtos.UserDTO;
import com.example.grocery_be.dtos.UserInRequestDTO;
import com.example.grocery_be.dtos.UserRequestDTO;
import com.example.grocery_be.dtos.UserResponseDTO;
import com.example.grocery_be.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody UserRequestDTO userRequestDTO){
        UserDTO userDTO = authService.signUp(userRequestDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserResponseDTO> signin(@RequestBody UserInRequestDTO userInRequestDTO){
        UserResponseDTO userResponseDTO = authService.signIn(userInRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }
}

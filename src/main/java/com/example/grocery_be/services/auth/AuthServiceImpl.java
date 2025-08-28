package com.example.grocery_be.services.auth;

import com.example.grocery_be.dtos.UserDTO;
import com.example.grocery_be.dtos.UserInRequestDTO;
import com.example.grocery_be.dtos.UserRequestDTO;
import com.example.grocery_be.dtos.UserResponseDTO;
import com.example.grocery_be.enities.User;
import com.example.grocery_be.enums.UserRole;
import com.example.grocery_be.mappers.UserMapper;
import com.example.grocery_be.repositories.UserRepository;
import com.example.grocery_be.utils.JwtUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @PostConstruct
    public void createAdminAccount() {
        Optional<User> optionalUser = userRepository.findByRole(UserRole.ADMIN);
        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setName("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setRole(UserRole.ADMIN);
            userRepository.save(user);
            System.out.println("Create successfully");
        } else {
            System.out.println("Account already exist");
        }
    }

    @Override
    public UserDTO signUp(UserRequestDTO userRequestDTO) {
        if (hasUserWithEmail(userRequestDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        ;

        User user = userMapper.toUserRequestDTO(userRequestDTO);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRole(UserRole.CUSTOMER);

        return userMapper.toUserDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO signIn(UserInRequestDTO userInRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userInRequestDTO.getEmail(),
                            userInRequestDTO.getPassword()
                    ));

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username and password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userInRequestDTO.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userInRequestDTO.getEmail());

        final String jwt = jwtUtils.generateToken(userDetails);
        UserResponseDTO response = new UserResponseDTO();
        if(optionalUser.isPresent()){
            response.setJwt(jwt);
            response.setEmail(optionalUser.get().getEmail());
            response.setRole(optionalUser.get().getRole());
            response.setId(optionalUser.get().getId());
        }



        return response;
    }

    @Override
    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}

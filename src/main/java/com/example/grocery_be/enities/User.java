package com.example.grocery_be.enities;

import com.example.grocery_be.enums.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;

@Document(collection = "users")
@Data
public class User implements UserDetails {

    @Id
    private String id;
    private String name;

    @Indexed(unique = true)
    private String email;
    private String password;

    private UserRole role ;
    private List<CartItem> cart;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}

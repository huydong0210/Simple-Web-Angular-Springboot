package com.dong.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Set<String> roles;
}

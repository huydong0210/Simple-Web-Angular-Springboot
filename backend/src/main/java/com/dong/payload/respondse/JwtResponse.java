package com.dong.payload.respondse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String fullName;
    private String username;
    private String email;
    private List<String> roles;

}

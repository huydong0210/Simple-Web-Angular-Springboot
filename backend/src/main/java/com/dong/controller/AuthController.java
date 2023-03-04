package com.dong.controller;

import com.dong.entity.Role;
import com.dong.entity.User;
import com.dong.entity.UserDetailsImpl;
import com.dong.jwt.JwtProvider;
import com.dong.payload.request.LoginRequest;
import com.dong.payload.request.SignUpRequest;
import com.dong.payload.respondse.JwtResponse;
import com.dong.payload.respondse.ResponseObject;
import com.dong.repository.RoleRepository;
import com.dong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth/")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("sign-in")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginRequest loginRequest) {
        if (userRepository.existsByUsername(loginRequest.getUsername()) == false) {
            return ResponseEntity.badRequest()
                    .body(new ResponseObject("failed", "Username was not exists", null));
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.createToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        System.out.println("dang nhap thanh cong");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "",
                        new JwtResponse(
                                jwt,
                                userDetails.getFullName(),
                                userDetails.getUsername(),
                                userDetails.getEmail(),
                                roles)
                ));

    }

    @PostMapping("sign-up")
    public ResponseEntity<ResponseObject> signUp(@RequestBody SignUpRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new ResponseObject("failed", "Username was exist", null));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            return ResponseEntity.badRequest()
                    .body(new ResponseObject("failed", "Email was exists", null));

        User user = new User();
        user.setFullName(signUpRequest.getFullName());
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        Set<String> strRole = signUpRequest.getRoles();
        List<Role> roles = new ArrayList<>();
        if (strRole.size() == 0) {
            roles.add(roleRepository.findByName(Role.USER)
                    .orElseThrow(() -> new RuntimeException("user role not found")));
        } else {
            strRole.forEach(role -> {
                switch (role) {
                    case "admin":
                        roles.add(roleRepository.findByName(Role.ADMIN)
                                .orElseThrow(() -> new RuntimeException("admin role not found")));
                        break;
                    case "mod":
                        roles.add(roleRepository.findByName(Role.MOD)
                                .orElseThrow(() -> new RuntimeException("mod role not found")));
                        break;
                    default:
                        roles.add(roleRepository.findByName(Role.USER)
                                .orElseThrow(() -> new RuntimeException("user role not found")));

                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "User registered successfully!", ""));

    }


}

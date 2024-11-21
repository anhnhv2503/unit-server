package com.anhnhv.unit.server.services.impl;

import com.anhnhv.unit.server.repository.UserRepository;
import com.anhnhv.unit.server.request.LoginRequest;
import com.anhnhv.unit.server.response.AuthenticationResponse;
import com.anhnhv.unit.server.security.jwt.JwtUtils;
import com.anhnhv.unit.server.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticationResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map((item) -> item.getAuthority())
                .toList();

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}

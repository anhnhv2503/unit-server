package com.anhnhv.unit.server.services.impl;

import com.anhnhv.unit.server.entities.RefreshToken;
import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.exception.RefreshTokenException;
import com.anhnhv.unit.server.repository.UserRepository;
import com.anhnhv.unit.server.dto.request.LoginRequest;
import com.anhnhv.unit.server.dto.request.LogoutRequest;
import com.anhnhv.unit.server.dto.request.RefreshTokenRequest;
import com.anhnhv.unit.server.dto.response.AuthenticationResponse;
import com.anhnhv.unit.server.config.security.jwt.JwtUtils;
import com.anhnhv.unit.server.config.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    public AuthenticationResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return AuthenticationResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request){
        String refreshToken = request.getRefreshToken();
        log.info(" >>>Token has been refreshed");
        return refreshTokenService.findByToken(refreshToken).map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return AuthenticationResponse.builder().token(token).refreshToken(refreshToken).build();
                })
                .orElseThrow(() -> new RefreshTokenException( "Refresh token is not in database!"));
    }

    public String logout(LogoutRequest request){
        String token = request.getToken();

        String username = jwtUtils.getUserNameFromJwtToken(token);
        User user = userRepository.findByUsername(username).get();

        if(refreshTokenService.deleteByUserId(user.getId()) != 0){
            return "Logout Success";
        }
        return null;
    }
}

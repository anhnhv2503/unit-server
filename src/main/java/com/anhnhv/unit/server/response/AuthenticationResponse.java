package com.anhnhv.unit.server.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationResponse {

    private String token;
    private String refreshToken;
}

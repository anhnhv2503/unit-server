package com.anhnhv.unit.server.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotNull(message = "Username is required")
    private String username;
    @NotNull(message = "Password is required")
    private String password;
}

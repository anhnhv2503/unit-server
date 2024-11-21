package com.anhnhv.unit.server.controller;

import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.request.UserRequest;
import com.anhnhv.unit.server.response.UserDTO;
import com.anhnhv.unit.server.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @GetMapping("/info")
    public ResponseEntity<UserDTO> getUserInfo(){
        return ResponseEntity.ok(userService.getAuthenticatedUser());
    }
}

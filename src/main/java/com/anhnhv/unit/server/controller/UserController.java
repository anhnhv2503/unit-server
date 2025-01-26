package com.anhnhv.unit.server.controller;

import com.anhnhv.unit.server.dto.request.UserRequest;
import com.anhnhv.unit.server.dto.response.UserDTO;
import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<User> getUserInfo(){
        return ResponseEntity.ok(userService.getAuthenticatedUser());
    }

    @GetMapping("/p/{userId}")
    public ResponseEntity<User> getUserInfo(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUser(@RequestParam String username){
        return ResponseEntity.ok(userService.searchUser(username));
    }

    @PostMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestParam("username") String username,
                                          @RequestParam("email") String email,
                                          @RequestParam("firstName") String firstName,
                                          @RequestParam("lastName") String lastName,
                                          @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        return ResponseEntity.ok(userService.update(username, email, firstName, lastName, imageFile));
    }
}

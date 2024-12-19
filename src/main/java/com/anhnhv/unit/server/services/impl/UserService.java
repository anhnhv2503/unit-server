package com.anhnhv.unit.server.services.impl;

import com.anhnhv.unit.server.entities.Role;
import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.mapper.UserMapper;
import com.anhnhv.unit.server.repository.RoleRepository;
import com.anhnhv.unit.server.repository.UserRepository;
import com.anhnhv.unit.server.dto.request.UserRequest;
import com.anhnhv.unit.server.dto.response.UserDTO;
import com.anhnhv.unit.server.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDTO register(UserRequest request) {

        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Username already in used");
        }else if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already in used");
        }
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new RuntimeException("Password does not match");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(request.getConfirmPassword()));
        user.setDateOfBirth(request.getDateOfBirth());
        user.setCreatedAt(new Date());
        user.setAvatar("https://avatars.githubusercontent.com/u/124599?v=4");
        Role roleUser = roleRepository.findByRoleName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role not exist"));
        user.setRoles(Set.of(roleUser));
        userRepository.save(user);
        UserMapper userMapper = new UserMapper();

        return userMapper.mapToUserDTO(user);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}

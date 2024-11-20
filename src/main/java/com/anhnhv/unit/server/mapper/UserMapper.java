package com.anhnhv.unit.server.mapper;

import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.response.UserDTO;

public class UserMapper {

    public UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setRole(user.getRoles().stream().findFirst().get().getRoleName());
        return userDTO;
    }

}

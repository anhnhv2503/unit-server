package com.anhnhv.unit.server.services;

import com.anhnhv.unit.server.dto.request.UserRequest;
import com.anhnhv.unit.server.dto.response.UserDTO;
import com.anhnhv.unit.server.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUserService {

    UserDTO register(UserRequest user);

    User getAuthenticatedUser();

    User getUserInfo(Long userId);

    List<UserDTO> searchUser(String username);

    UserDTO update(String username, String email, String firstName, String lastName, MultipartFile imageFile) throws IOException;
}

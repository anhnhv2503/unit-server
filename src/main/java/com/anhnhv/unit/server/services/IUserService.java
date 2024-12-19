package com.anhnhv.unit.server.services;

import com.anhnhv.unit.server.dto.request.UserRequest;
import com.anhnhv.unit.server.dto.response.UserDTO;
import com.anhnhv.unit.server.entities.User;

public interface IUserService {

    UserDTO register(UserRequest user);

    User getAuthenticatedUser();
}

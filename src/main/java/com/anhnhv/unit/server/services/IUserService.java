package com.anhnhv.unit.server.services;

import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.request.UserRequest;
import com.anhnhv.unit.server.response.UserDTO;

public interface IUserService {

    UserDTO register(UserRequest user);

    UserDTO getAuthenticatedUser();
}

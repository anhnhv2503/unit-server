package com.anhnhv.unit.server.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String username;
    private String email;
    private Date dateOfBirth;
    private String avatar;
    private Date createdAt;
    private String role;

}

package com.java.springoauth2server.models;

import com.java.springoauth2server.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {
    private String username;
    private String password;
    private List<Long> roles;
}

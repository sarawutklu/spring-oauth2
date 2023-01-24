package com.java.springoauth2server.service.impl;

import com.java.springoauth2server.entity.Role;
import com.java.springoauth2server.entity.User;
import com.java.springoauth2server.entity.UserRoles;
import com.java.springoauth2server.models.UserRequest;
import com.java.springoauth2server.repository.UserRepository;
import com.java.springoauth2server.repository.UserRolesRepository;
import com.java.springoauth2server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void singup(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User userSeved = userRepository.save(user);
        List<UserRoles> userRolesList = new ArrayList<UserRoles>();
        for (var roleId : userRequest.getRoles()){
            UserRoles userRoles = new UserRoles();
            userRoles.setRoleId(roleId);
            userRoles.setUserId(userSeved.getId());
            userRolesList.add(userRoles);
        }
        userRolesRepository.saveAll(userRolesList);
    }
}

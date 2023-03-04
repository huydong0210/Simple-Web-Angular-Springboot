package com.dong.mapper;

import com.dong.DTO.*;
import com.dong.entity.*;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public UserDTO userToUserDTO(User user) {
        return new UserDTO(user);
    }
    public RoleDTO roleToRoleDTO(Role role) {
        return new RoleDTO(role);
    }

    public User userDtoToUser(UserDTO userDTO){
        User user =new User();
        user.setFullName(user.getFullName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        return user;
    }


}

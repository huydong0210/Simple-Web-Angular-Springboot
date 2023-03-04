package com.dong.DTO;

import com.dong.entity.Role;
import com.dong.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private long id;
    private String fullName;
    private String username;
    private String email;
    private List<String> roles=new ArrayList<>();
    public UserDTO(User user){
        this.id=user.getId();
        this.fullName=user.getFullName();
        this.username=user.getUsername();
        this.email=user.getEmail();
        for (Role role : user.getRoles()){
            this.roles.add(role.getName());
        }
    }

}

package com.dong.DTO;

import com.dong.entity.Permission;
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
public class RoleDTO {
    private long id;
    private String name;
    private List<String> users=new ArrayList<>();
    private List<String> permissions=new ArrayList<>();

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        for (User user : role.getUsers()) {
            this.users.add(user.getUsername());
        }
    }

}

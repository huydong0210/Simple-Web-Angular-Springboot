package com.dong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "role")
public class Role extends Base{
    public static final String ADMIN = "admin";
    public static final String MOD = "mod";
    public static final String USER = "user";
    @Column
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users=new ArrayList<>();
    public Role(){

    }
    public Role(String name){
        this.name =name;
    }
}

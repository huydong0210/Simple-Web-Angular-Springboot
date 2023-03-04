package com.dong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "permission")
public class Permission extends Base {
    @Column
    private String name;
//    @ManyToMany(mappedBy = "permissions")
//    private List<Role> roles;

}

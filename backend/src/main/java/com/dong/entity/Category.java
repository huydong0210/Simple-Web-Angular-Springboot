package com.dong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category  extends Base{
    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;


}

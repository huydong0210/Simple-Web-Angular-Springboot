package com.dong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends Base {
    @Column
    private String name;

    @Column
    private double price;

    @Column
    private Date updatedDate;
    @Column
    private String updatedBy;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}

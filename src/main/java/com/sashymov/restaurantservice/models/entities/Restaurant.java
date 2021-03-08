package com.sashymov.restaurantservice.models.entities;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int dishCount;
    private String hours;
    private String phone;
    private String social;
    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;
    @Column(name = "orderNum")
    private int orderNum;

}

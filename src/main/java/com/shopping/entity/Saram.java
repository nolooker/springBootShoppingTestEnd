package com.shopping.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "sarams")
@Getter @Setter @ToString
public class Saram {

    @Id
    @Column(name = "saram_id", nullable = false)
    private String id ;

    @Column(nullable = false, length = 30)
    private String name ;

    @Column(nullable = false)
    private Long salary ;

    @Column(nullable = false)
    private String address ;
}

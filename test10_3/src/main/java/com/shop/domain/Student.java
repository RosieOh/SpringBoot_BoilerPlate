package com.shop.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY) //Seat와 1:1연관
    @JoinColumn(name = "seat_id")
    private Seat seat;
}

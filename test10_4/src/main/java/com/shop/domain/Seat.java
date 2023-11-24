package com.shop.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    private String rowId;

    private Integer colId;

    @OneToOne(mappedBy = "seat", fetch = FetchType.LAZY)
    private Student student;
}

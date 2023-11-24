package com.shop.domain;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//수강생
@Entity
@Data
public class Participants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participants_id")
    private Long id;

    private String name;

    private Integer age;

    @OneToMany(mappedBy = "participants")
    private List<ParticipantsLecture> articipantsLectures = new ArrayList<>();
}

package com.shop.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//강의
@Entity
@Data
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    private String name;

    private String teacherName;

    @OneToMany(mappedBy = "lecture")
    private List<ParticipantsLecture> articipantsLectures = new ArrayList<>();

}

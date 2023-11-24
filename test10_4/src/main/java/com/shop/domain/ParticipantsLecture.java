package com.shop.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
//수강신청
@Entity
@Data
public class ParticipantsLecture {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "participantsLecture_id")
    private Long id;

    private LocalDateTime registerDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participants_id")
    private Participants participants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
}

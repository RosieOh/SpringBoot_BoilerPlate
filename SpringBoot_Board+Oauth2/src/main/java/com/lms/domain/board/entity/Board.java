package com.lms.domain.board.entity;

import com.lms.global.cosntant.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;


    private String boardType;

    @Column
    private Long fileId;

    private String writer;

    public void create(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void change(String title, String content) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.fileId = fileId;
    }

    @Builder
    public Board(Long id, String title, String content, String writer, Long fileId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.fileId = fileId;
    }
}

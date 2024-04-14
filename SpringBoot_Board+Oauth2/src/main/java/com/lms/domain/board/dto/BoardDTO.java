package com.lms.domain.board.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long id;

    @NotEmpty
    @Size(max = 500)
    private String title;

    @NotEmpty
    @Size(max = 2000)
    private String content;

    @NotEmpty
    @Size(max = 50)
    private String boardType;

    @NotNull
    private Long fileId;

    @NotEmpty
    @Size(max = 50)
    private String writer;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private List<String> fileNames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

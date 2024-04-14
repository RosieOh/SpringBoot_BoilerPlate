package com.lms.domain.file.dto;

import com.lms.domain.file.entity.File;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDTO {
    private Long id;
    private String originFileName;
    private String filePath;
    private String fileName;

    public File toEntity() {
      File build = File.builder()
                .id(id)
                .originFileName(originFileName)
                .filePath(filePath)
                .fileName(fileName)
                .build();
        return build;
    }

    @Builder
    public FileDTO(Long id, String originFileName, String filePath, String fileName) {
        this.id = id;
        this.originFileName = originFileName;
        this.filePath = filePath;
        this.fileName = fileName;
    }

}

package com.lms.domain.file.service;

import com.lms.domain.file.dto.FileDTO;
import com.lms.domain.file.entity.File;
import com.lms.domain.file.repository.FileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public Long saveFile(FileDTO fileDTO) {
        return fileRepository.save(fileDTO.toEntity()).getId();
    }

    @Transactional
    public FileDTO getFile(Long id) {
        File file = fileRepository.findById(id).get();

        FileDTO fileDTO = FileDTO.builder()
                .id(id)
                .originFileName(file.getOriginFileName())
                .fileName(file.getFileName())
                .filePath(file.getFilePath())
                .build();
        return fileDTO;
    }

}
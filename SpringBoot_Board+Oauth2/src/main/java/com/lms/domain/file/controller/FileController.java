package com.lms.domain.file.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@Log4j2
@RequiredArgsConstructor
public class FileController {
    private static final String FILE_DIRECTORY = System.getProperty("user.dir") + "/files";

    @GetMapping("/notice/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws Exception {
        // 파일 경로 설정
        Path filePath = Paths.get(FILE_DIRECTORY).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());
        log.info("어디어디? " + filePath);
        log.info("어디어디?2 " + resource);

        // 파일이 존재하는지 확인
        if(resource.exists() && resource.isReadable()) {
            // 다운로드를 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());

            // 파일을 응답으로 전송
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } else {
            // 파일이 존재하지 않을 경우 404 에러 응답
            return ResponseEntity.notFound().build();
        }
    }

}

package com.lms.global.prometheus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {


    // 트레픽 테스트를 위한 테스트 트레픽 구성
    @GetMapping("/end-point1")
    public String endPoint1() {
        return "Metrics for endPoint1";
    }

    @GetMapping("/end-point2")
    public String endpoint2() {
        return "Metrics for endPoint2";
    }

    @GetMapping("/end-point3")
    public String endpoint3() {
        return "Metrics for endPoint3";
    }

}
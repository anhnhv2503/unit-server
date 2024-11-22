package com.anhnhv.unit.server.controller;

import com.anhnhv.unit.server.services.impl.AmazonClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class AppController {

//    private final AmazonClientService amazonClientService;

    @GetMapping
    public String hello() {
        return "Hello World!";
    }

//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam(name = "file") MultipartFile file){
//        log.info("Start upload");
//        return amazonClientService.uploadFile(file);
//    }
}

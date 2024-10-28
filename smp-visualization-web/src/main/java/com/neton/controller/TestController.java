package com.neton.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TheSunshine
 * @date 2024-10-28 18:02:37
 */
@RestController
@RequestMapping("/v1/test")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<Object> test(@RequestParam String id){

        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}

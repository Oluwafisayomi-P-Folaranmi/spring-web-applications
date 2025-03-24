package com.opf.shopfull.controller;

import com.opf.shopfull.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<ApiResponse> HomeControllerHandler() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Welcome to shopfull.");
        apiResponse.setTime(LocalDateTime.now());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

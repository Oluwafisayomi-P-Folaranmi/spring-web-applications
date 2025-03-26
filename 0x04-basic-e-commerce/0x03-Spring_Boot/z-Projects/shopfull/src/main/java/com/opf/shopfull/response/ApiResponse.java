package com.opf.shopfull.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse {

    private String message;

    private LocalDateTime time;

}

package com.bloque3.product_service.exceptions;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {
    private HttpStatus status;
    private String error;
    private String message;
    private String path;
    private Instant timestamp;
    private List<String> details;
}

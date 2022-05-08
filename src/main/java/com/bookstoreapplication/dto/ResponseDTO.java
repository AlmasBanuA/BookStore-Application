package com.bookstoreapplication.dto;

import lombok.Data;

/**
 * Data transfer object to display output in message with object format
 */
@Data
public class ResponseDTO {
    private String message;
    private Object data;
    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseDTO() {
    }
}
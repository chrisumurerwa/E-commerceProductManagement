package org.example.productmanagement.Dto;

public class ResponseDto {
    private String message;
    private boolean success;

    public ResponseDto(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // Getters
    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }
}

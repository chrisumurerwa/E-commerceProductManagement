package org.example.productmanagement.GlobalExceptionHandling;

public class resourceNotFoundException extends RuntimeException {
    public resourceNotFoundException(String message) {
        super(message);
    }

}

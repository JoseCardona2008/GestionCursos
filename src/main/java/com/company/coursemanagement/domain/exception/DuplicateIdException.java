package com.company.coursemanagement.domain.exception;

public class DuplicateIdException extends BusinessException {
    public DuplicateIdException(String message) {
        super(message);
    }
}
package com.company.coursemanagement.domain.exception;

public class StudentNotFoundException extends BusinessException {
    public StudentNotFoundException(String id) {
        super("Estudiante no encontrado: " + id);
    }
}

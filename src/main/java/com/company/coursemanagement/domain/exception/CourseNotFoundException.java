package com.company.coursemanagement.domain.exception;

public class CourseNotFoundException extends BusinessException {
    public CourseNotFoundException(String id) {
        super("Curso no encontrado: " + id);
    }
}

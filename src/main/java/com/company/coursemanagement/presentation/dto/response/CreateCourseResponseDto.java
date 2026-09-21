package com.company.coursemanagement.presentation.dto.response;

public record CreateCourseResponseDto(
        Long id,
        String code,
        String name,
        String description,
        Integer maxCapacity
) {
    public static CreateCourseResponseDto from(com.company.coursemanagement.domain.model.Course course) {
        return new CreateCourseResponseDto(
                course.getId(),
                course.getCode(),
                course.getName(),
                course.getDescription(),
                course.getMaxCapacity()
        );
    }
}
package com.company.coursemanagement.presentation.dto.request;

public record CreateCourseRequestDto(
        String code,
        String name,
        String description,
        Integer maxCapacity
) {}
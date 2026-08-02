package com.company.coursemanagement.domain.model;

import java.util.Objects;

public final class Course {
    private final Long id;
    private final String code;
    private final String name;
    private final String description;
    private final Integer maxCapacity;

    public Course(Long id, String code, String name, String description, Integer maxCapacity) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("code cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or blank");
        }
        if (maxCapacity == null || maxCapacity <= 0) {
            throw new IllegalArgumentException("maxCapacity must be a positive number");
        }
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.maxCapacity = maxCapacity;
    }

    public Course(String code, String name, String description, Integer maxCapacity) {
        this(null, code, name, description, maxCapacity);
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public Course withId(Long id) {
        return new Course(id, code, name, description, maxCapacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return Objects.equals(id, course.id) && Objects.equals(code, course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }

    @Override
    public String toString() {
        return "Course{id=" + id + ", code='" + code + '\'' +
                ", name='" + name + '\'' + ", description='" + description + '\'' +
                ", maxCapacity=" + maxCapacity + '}';
    }
}

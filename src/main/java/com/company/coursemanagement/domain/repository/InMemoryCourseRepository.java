package com.company.coursemanagement.domain.repository;

import com.company.coursemanagement.domain.model.Course;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCourseRepository extends AbstractInMemoryRepository implements CourseRepository {
    private final Map<Long, Course> store = new LinkedHashMap<>();

    @Override
    public Course save(Course course) {
        Long id = course.getId();
        if (id == null) {
            id = nextId();
            course = course.withId(id);
        }
        store.put(id, course);
        return course;
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Course> findByCode(String code) {
        return store.values().stream()
                .filter(c -> c.getCode().equals(code))
                .findFirst();
    }

    @Override
    public List<Course> findAll() {
        return List.copyOf(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return store.containsKey(id);
    }
}

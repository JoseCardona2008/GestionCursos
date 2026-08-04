package com.company.coursemanagement.domain.repository;

import com.company.coursemanagement.domain.model.Enrollment;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryEnrollmentRepository extends AbstractInMemoryRepository implements EnrollmentRepository {
    private final Map<Long, Enrollment> store = new LinkedHashMap<>();

    @Override
    public Enrollment save(Enrollment enrollment) {
        Long id = enrollment.getId();
        if (id == null) {
            id = nextId();
            enrollment = enrollment.withId(id);
        }
        store.put(id, enrollment);
        return enrollment;
    }

    @Override
    public Optional<Enrollment> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Enrollment> findAll() {
        return List.copyOf(store.values());
    }

    @Override
    public List<Enrollment> findByStudentId(Long studentId) {
        return store.values().stream()
                .filter(e -> e.getStudentId().equals(studentId))
                .toList();
    }

    @Override
    public List<Enrollment> findByCourseId(Long courseId) {
        return store.values().stream()
                .filter(e -> e.getCourseId().equals(courseId))
                .toList();
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

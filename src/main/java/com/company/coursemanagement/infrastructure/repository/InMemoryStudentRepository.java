package com.company.coursemanagement.infrastructure.repository;

import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.domain.repository.StudentRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryStudentRepository extends AbstractInMemoryRepository implements StudentRepository {
    private final Map<Long, Student> store = new LinkedHashMap<>();

    @Override
    public Student save(Student student) {
        Long id = student.getId();
        if (id == null) {
            id = nextId();
            student = student.withId(id);
        }
        store.put(id, student);
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Student> findAll() {
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

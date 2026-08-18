package com.company.coursemanagement.presentation.rest;

import com.company.coursemanagement.infrastructure.entity.CourseEntity;
import com.company.coursemanagement.infrastructure.repository.CourseJpaRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cursos")
public class CourseRestController {

    private final CourseJpaRepository courseJpaRepository;

    public CourseRestController(CourseJpaRepository courseJpaRepository) {
        this.courseJpaRepository = courseJpaRepository;
    }

    @GetMapping
    public List<CourseEntity> listar() {
        return courseJpaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEntity> buscarPorId(@PathVariable Long id) {
        return courseJpaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CourseEntity crear(@RequestBody CourseEntity course) {
        return courseJpaRepository.save(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (courseJpaRepository.existsById(id)) {
            courseJpaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

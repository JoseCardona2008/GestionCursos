package com.company.coursemanagement.presentation.rest;

import com.company.coursemanagement.infrastructure.entity.StudentEntity;
import com.company.coursemanagement.infrastructure.repository.StudentJpaRepository;
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
@RequestMapping("/api/estudiantes")
public class StudentRestController {

    private final StudentJpaRepository studentJpaRepository;

    public StudentRestController(StudentJpaRepository studentJpaRepository) {
        this.studentJpaRepository = studentJpaRepository;
    }

    @GetMapping
    public List<StudentEntity> listar() {
        return studentJpaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> buscarPorId(@PathVariable Long id) {
        return studentJpaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentEntity crear(@RequestBody StudentEntity student) {
        return studentJpaRepository.save(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (studentJpaRepository.existsById(id)) {
            studentJpaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Student> getStudentsByAge(@RequestParam("age") int age) {
        return studentService.getStudentByAge(age);
    }
}

package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
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
    public ResponseEntity getStudentsByAgeBetween(@RequestParam("min") int minAge, @RequestParam("max") int maxAge) {
        return ResponseEntity.ok(studentService.findByAgeBetween(minAge, maxAge));
    }

    @GetMapping("/{id}/faculty")
    public Faculty getFaculty(@PathVariable Long id) {
        return studentService.getFaculty(id);
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-number-students")
    public Integer getNumberOfStudents() {
        return studentService.getNumberOfStudents();
    }

    @GetMapping("/get-average-age")
    public Integer getAverageAge() {
        return studentService.getAverageAgeOfStudents();
    }

    @GetMapping("/get-latest-students")
    public List<Student> getLatestStudents() {
        return studentService.getFiveLatestStudents();
    }
}

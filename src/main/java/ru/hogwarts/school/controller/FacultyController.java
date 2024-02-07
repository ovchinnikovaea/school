package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.impl.FacultyServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyServiceImpl facultyServise;

    public FacultyController(FacultyServiceImpl facultyServise) {
        this.facultyServise = facultyServise;
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyServise.addFaculty(faculty);
    }

    @GetMapping("/{id}")
    public Faculty get(@PathVariable Long id) {
        return facultyServise.getFaculty(id);
    }

    @PutMapping("/{id}")
    public Faculty update(@PathVariable Long id, @RequestBody Faculty faculty) {
        return facultyServise.updateFaculty(id, faculty);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        facultyServise.deleteFaculty(id);
    }

    @GetMapping
    public ResponseEntity getFacultiesByNameOrColor(@RequestParam(required = false, name = "name") String name,
                                                    @RequestParam(required = false, name = "color") String color) {
        return ResponseEntity.ok(facultyServise.findByNameOrColorIgnoreCase(name, color));
    }

    @GetMapping("/{id}/students")
    public Collection<Student> getStudents(@PathVariable Long id) {
        return facultyServise.getStudents(id);

    }
}

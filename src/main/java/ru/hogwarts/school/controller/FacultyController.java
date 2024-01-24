package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.List;

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

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Long id) {
        facultyServise.deleteFaculty(id);
    }

    @GetMapping
    public List<Faculty> getFacultiesByColor(@RequestParam("color") String color) {
        return facultyServise.getFacultyByColor(color);
    }
}

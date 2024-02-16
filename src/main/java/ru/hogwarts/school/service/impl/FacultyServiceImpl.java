package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultiesNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow(() -> new FacultiesNotFoundException("Факультет не найден!"));
    }

    @Override
    public List<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findAll()
                .stream()
                .filter(it -> it.getColor().equals(color))
                .collect(Collectors.toList());
    }

    @Override
    public Faculty updateFaculty(long id, Faculty faculty) {
        return facultyRepository.findById(id).map(facultyFromDb -> {
            facultyFromDb.setName(faculty.getName());
            facultyFromDb.setColor(faculty.getColor());
            return facultyRepository.save(facultyFromDb);
        }).orElseThrow();
    }

    @Override
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> findByNameOrColorIgnoreCase(String name, String color) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> getStudents(long id) {
        return facultyRepository.findById(id)
                .map(Faculty::getStudents)
                .orElseThrow(() -> new FacultiesNotFoundException("Факультет не найден!"));

    }

}

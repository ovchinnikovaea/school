package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultiesNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    @Override
    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(long id) {
        logger.info("Was invoked method for get faculty");
        logger.error("There is not faculty with id = " + id);
        return facultyRepository.findById(id).orElseThrow(() -> new FacultiesNotFoundException("Факультет не найден!"));
    }

    @Override
    public List<Faculty> getFacultyByColor(String color) {
        logger.info("Was invoked method for get faculty by color faculty");
        return facultyRepository.findAll()
                .stream()
                .filter(it -> it.getColor().equals(color))
                .collect(Collectors.toList());
    }

    @Override
    public Faculty updateFaculty(long id, Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        logger.error("There is not faculty with id = " + id);
        return facultyRepository.findById(id).map(facultyFromDb -> {
            facultyFromDb.setName(faculty.getName());
            facultyFromDb.setColor(faculty.getColor());
            return facultyRepository.save(facultyFromDb);
        }).orElseThrow();
    }

    @Override
    public void deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> findByNameOrColorIgnoreCase(String name, String color) {
        logger.info("Was invoked method for find faculty by name or color");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> getStudents(long id) {
        logger.info("Was invoked method for get faculty");
        logger.error("There is not faculty with id = " + id);
        return facultyRepository.findById(id)
                .map(Faculty::getStudents)
                .orElseThrow(() -> new FacultiesNotFoundException("Факультет не найден!"));

    }

    @Override
    public String getLongestName() {
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

}

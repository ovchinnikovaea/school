package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentsNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student addStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(long id) {
        logger.info("Was invoked method for get student");
        logger.error("There is not student with id = " + id);
        return studentRepository.findById(id).orElseThrow(() -> new StudentsNotFoundException("Студент не найден!"));
    }

    @Override
    public List<Student> getStudentByAge(int age) {
        logger.info("Was invoked method for get student by age student");
        return studentRepository.findAll()
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public Student updateStudent(long id, Student student) {
        logger.info("Was invoked method for update student");
        logger.error("There is not student with id = " + id);
        return studentRepository.findById(id).map(studentFromDb -> {
            studentFromDb.setName(student.getName());
            studentFromDb.setAge(student.getAge());
            return studentRepository.save(studentFromDb);
        }).orElseThrow();
    }

    @Override
    public void deleteStudent(Long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for find student by age between student");
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getFaculty(Long id) {
        logger.info("Was invoked method for get faculty");
        logger.error("There is not student with id = " + id);
        return studentRepository.findById(id)
                .map(Student::getFaculty)
                .orElseThrow(() -> new StudentsNotFoundException("Студент не найден!"));
    }

    @Override
    public Integer getNumberOfStudents() {
        logger.info("Was invoked method for get number of student");
        return studentRepository.getNumberOfStudents();
    }

    @Override
   public Integer getAverageAgeOfStudents() {
        logger.info("Was invoked method for get average age of students");
        return studentRepository.getAverageAgeOfStudents();
    }

    @Override
   public List<Student> getFiveLatestStudents() {
        logger.info("Was invoked method for get 5 latest students");
        return studentRepository.getFiveLatestStudents();
    }

    @Override
    public List<String> getStudentsWithNamesStartA() {
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(it -> it.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Double getAverageAgeWithStream() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average().orElse(0);
    }

}

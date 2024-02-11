package ru.hogwarts.school.exception;

public class StudentsNotFoundException extends RuntimeException {
    public StudentsNotFoundException(String massage) {
        super(massage);
    }
}

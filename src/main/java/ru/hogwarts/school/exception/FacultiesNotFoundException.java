package ru.hogwarts.school.exception;

public class FacultiesNotFoundException extends RuntimeException {

    public FacultiesNotFoundException(String massage) {
        super(massage);
    }
}

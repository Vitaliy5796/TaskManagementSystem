package ru.sidorov.taskmanagementsystem.models.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class TaskRestException extends IllegalArgumentException {
    private HttpStatus httpStatus;

    public TaskRestException(HttpStatus httpStatus, String msg) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}

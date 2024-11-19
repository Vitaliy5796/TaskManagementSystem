package ru.sidorov.taskmanagementsystem.models.dto.common;

import org.springframework.http.HttpStatus;
import ru.sidorov.taskmanagementsystem.models.enums.Result;
import ru.sidorov.taskmanagementsystem.models.exception.TaskRestException;

public class TmsResponseErrorEntity<T> extends TmsResponseEntity<T> {

    public TmsResponseErrorEntity(HttpStatus httpStatus, String operationInfo) {
        super(httpStatus, Result.ERROR, null, operationInfo);
    }

    public TmsResponseErrorEntity(String operationInfo) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, operationInfo);
    }

    public TmsResponseErrorEntity(TaskRestException e) {
        this(e.getHttpStatus(), e.getMessage());
    }

    public TmsResponseErrorEntity(Exception e) {
        this(e.getMessage());
    }
}

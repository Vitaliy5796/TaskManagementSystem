package ru.sidorov.taskmanagementsystem.models.dto.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sidorov.taskmanagementsystem.models.enums.Result;

public class TmsResponseEntity<T> extends ResponseEntity<OperationResult<T>> {

    public TmsResponseEntity(HttpStatus httpStatus, Result operationResult, T operationResultObject, String operationInfo) {
        super(new OperationResult<>(operationResult, operationResultObject, operationInfo), httpStatus);
    }
}

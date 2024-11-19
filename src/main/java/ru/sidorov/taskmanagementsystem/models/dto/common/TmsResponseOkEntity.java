package ru.sidorov.taskmanagementsystem.models.dto.common;

import org.springframework.http.HttpStatus;
import ru.sidorov.taskmanagementsystem.models.enums.Result;

public class TmsResponseOkEntity<T> extends TmsResponseEntity<T> {

    public TmsResponseOkEntity(HttpStatus httpStatus, T operationResultObject)
    {
        super(httpStatus, Result.OK, operationResultObject, null);
    }

    public TmsResponseOkEntity(T operationResultObject)
    {
        this(HttpStatus.OK, operationResultObject);
    }

    public TmsResponseOkEntity()
    {
        this(HttpStatus.OK, null);
    }
}

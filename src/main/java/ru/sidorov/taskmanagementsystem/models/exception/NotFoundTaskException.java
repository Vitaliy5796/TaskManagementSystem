package ru.sidorov.taskmanagementsystem.models.exception;

public class NotFoundTaskException extends NotFoundException {

    public static final String ERROR_MSG = "Задача не найдена";

    public static final String ERROR_MSG_USER_WITH_ID = "Задача с %d не найдена";

    public NotFoundTaskException() {
        super(ERROR_MSG);
    }

    public NotFoundTaskException(Integer taskId) {
        super(String.format(ERROR_MSG_USER_WITH_ID, taskId));
    }
}

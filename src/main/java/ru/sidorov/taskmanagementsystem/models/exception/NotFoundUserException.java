package ru.sidorov.taskmanagementsystem.models.exception;

public class NotFoundUserException extends NotFoundException {

    public static final String ERROR_MSG = "Пользователь не найден";

    public static final String ERROR_MSG_USER_WITH_ID = "Пользователь c id = %d не найден";

    public NotFoundUserException() {
        super(ERROR_MSG);
    }

    public NotFoundUserException(Integer userId) {
        super(String.format(ERROR_MSG_USER_WITH_ID, userId));
    }
}

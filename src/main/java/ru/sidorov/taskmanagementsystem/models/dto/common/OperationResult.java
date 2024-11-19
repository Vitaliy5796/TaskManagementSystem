package ru.sidorov.taskmanagementsystem.models.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sidorov.taskmanagementsystem.models.enums.Result;

@ApiModel(description = "Сущность, описывающая результат выполнения операции")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationResult<T> {
    @ApiModelProperty(notes = "Результат выполнения операции")
    private Result operationResult;
    @ApiModelProperty(notes = "Объект, возвращаемый операцией")
    private T object;
    @ApiModelProperty(notes = "Дополнительная информация к результату выполнения (как правило описание ошибки)")
    private String operationInfo;
}

package ru.sidorov.taskmanagementsystem.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String login;
    private String token;

}

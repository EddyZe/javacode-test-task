package ru.edd.javacodetesttask.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BadResponse {

    private String message;
    private LocalDateTime timestamp;

}

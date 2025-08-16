package com.mayur29.SetaPakki.SeatPakki.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class ErrorResponse {
    private int statusCode;
    private String message;
}

package com.practicas.TicTacTareas.exception;


public class EmailIsExistente extends RuntimeException {
    public EmailIsExistente(String message) {
        super(message);
    }
}
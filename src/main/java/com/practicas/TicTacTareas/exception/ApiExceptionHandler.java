package com.practicas.TicTacTareas.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.ConstraintViolation;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice // <- para JSON
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ProblemDetail handleTaskNotFound(TaskNotFoundException ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Recurso no encontrado");
        pd.setDetail(ex.getMessage());
        pd.setProperty("timestamp", Instant.now());
        pd.setProperty("path", req.getRequestURI());
        pd.setProperty("code", "TASK_NOT_FOUND");
        return pd;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // @Valid en @RequestBody
    public ProblemDetail handleBodyValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pd.setTitle("Datos inválidos");
        pd.setDetail("Uno o más campos no cumplen las validaciones.");
        pd.setProperty("errors", errors);
        pd.setProperty("path", req.getRequestURI());
        return pd;
    }

    @ExceptionHandler(ConstraintViolationException.class) // @Validated en @RequestParam/@PathVariable
    public ProblemDetail handleParamValidation(ConstraintViolationException ex, HttpServletRequest req) {
        Map<String, String> errors = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        ConstraintViolation::getMessage,   // forma más limpia
                        (a, b) -> a,                      // si hay duplicados, me quedo con el primero
                        LinkedHashMap::new                // mantiene orden de inserción
                ));

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Parámetros inválidos");
        pd.setDetail("Uno o más parámetros no cumplen validación.");
        pd.setProperty("errors", errors);
        pd.setProperty("path", req.getRequestURI());
        return pd;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class) // JSON mal formado / tipos erróneos
    public ProblemDetail handleUnreadable(HttpMessageNotReadableException ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Cuerpo de la petición inválido");
        pd.setDetail("JSON mal formado o tipos de dato incorrectos.");
        pd.setProperty("path", req.getRequestURI());
        return pd;
    }

    @ExceptionHandler(ResponseStatusException.class) // si lanzas nuevas ResponseStatusException(...)
    public ProblemDetail handleResponseStatus(ResponseStatusException ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatus(ex.getStatusCode());
        pd.setTitle("Error de negocio");
        pd.setDetail(ex.getReason());
        pd.setProperty("path", req.getRequestURI());
        return pd;
    }

    @ExceptionHandler(Exception.class) // Fallback
    public ProblemDetail handleUnexpected(Exception ex, HttpServletRequest req) {
        // Aquí loguea el stacktrace con tu logger preferido
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Error interno");
        pd.setDetail("Ocurrió un error inesperado. Intenta más tarde.");
        pd.setProperty("path", req.getRequestURI());
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }

    @ExceptionHandler(EmailIsExistente.class)
    public ProblemDetail handleEmailIsExistente(EmailIsExistente ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pd.setTitle("Usuario Existente");
        pd.setDetail(ex.getMessage());
        pd.setProperty("path", req.getRequestURI());
        return pd;
    }
}
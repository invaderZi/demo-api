package com.zetalabs.mood.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zetalabs.mood.app.exception.usuario.EmailJaExistenteException;
import com.zetalabs.mood.app.exception.usuario.UsernameJaExistenteException;
import com.zetalabs.mood.app.exception.usuario.UsuarioNaoEncontradoByUsernameException;
import com.zetalabs.mood.app.exception.usuario.UsuarioNaoEncontradoException;

@ControllerAdvice
public class GlobalExceptionHandler {
	

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Servidor Indisponivel");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
    @ExceptionHandler(UsuarioNaoEncontradoByUsernameException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioNaoEncontradoByUsername(UsuarioNaoEncontradoByUsernameException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(EmailJaExistenteException.class)
    public ResponseEntity<ErrorResponse> handleEmailJaExistente(EmailJaExistenteException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    
    @ExceptionHandler(UsernameJaExistenteException.class)
    public ResponseEntity<ErrorResponse> handleUsernameJaExistente(UsernameJaExistenteException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

}

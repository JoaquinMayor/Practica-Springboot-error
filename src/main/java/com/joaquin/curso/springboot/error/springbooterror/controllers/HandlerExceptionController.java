package com.joaquin.curso.springboot.error.springbooterror.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.joaquin.curso.springboot.error.springbooterror.exceptions.UserNotFoundException;
import com.joaquin.curso.springboot.error.springbooterror.models.Error;

@RestControllerAdvice //Esto se usa para el manejo de errores o exception, se encuentra mapeado a estas cosas
public class HandlerExceptionController {
    
    @ExceptionHandler(ArithmeticException.class) //Se mapea a este error, si se mapea a mas de una se ponen entre {}
    public ResponseEntity<Error> divisionByZero(Exception ex){ //Response entity sirve para manejar errores, el signo de pregunta sirve para poder poner cualquier elemento
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error division por cero!");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
        return ResponseEntity.internalServerError().body(error); //es internal servererror, porque es el erroe 500 que nos mandaba la division por 0 y en body va el texto que queresmos mandar
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //Como se hace con un mapa de esta manera se pasa el error
    public Map<String,Object> numberFormatException(Exception ex){
        
        Map<String, Object> error = new HashMap<>();
        error.put("Date", new Date());
        error.put("error","Numero incorrecto o invalido, no tiene formato de digito");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

    @ExceptionHandler({NullPointerException.class, HttpMessageNotWritableException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //Como se hace con un mapa de esta manera se pasa el error
    public Map<String,Object> userNotFoundEception(Exception ex){
        
        Map<String, Object> error = new HashMap<>();
        error.put("Date", new Date());
        error.put("error","Usuario o roll no existente");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

    @ExceptionHandler(NoHandlerFoundException.class) //Ver en el properties como se configuran 2 comandos para poder modificar el error 404
    public ResponseEntity<Error> notFoundEx(NoHandlerFoundException ex){

        Error error = new Error();
        error.setDate(new Date());
        error.setError("Api rest no encontrado");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        
        return ResponseEntity.status(404).body(error);
    }
}

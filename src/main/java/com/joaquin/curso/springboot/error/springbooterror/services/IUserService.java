package com.joaquin.curso.springboot.error.springbooterror.services;

import java.util.List;
import java.util.Optional;

import com.joaquin.curso.springboot.error.springbooterror.models.domain.User;

public interface IUserService {
    
    List<User> finAll();
    Optional<User> findById(Long id); //Es contenedor que puedo o no contener al objeto
    
}

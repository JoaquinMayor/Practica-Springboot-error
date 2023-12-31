package com.joaquin.curso.springboot.error.springbooterror.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquin.curso.springboot.error.springbooterror.models.domain.User;
import com.joaquin.curso.springboot.error.springbooterror.repository.UserRepository;
@Service
public class UserServiceImp implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> finAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = userRepository.findById(id);
        //if(user == null){
          //  throw new UserNotFoundException("Usuario inexistente!");
        //}
        return Optional.ofNullable(user); //devuelve un usuario si existe o un empty si no existe
    }
    

}

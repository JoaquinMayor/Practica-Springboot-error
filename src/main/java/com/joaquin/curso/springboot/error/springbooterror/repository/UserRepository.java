package com.joaquin.curso.springboot.error.springbooterror.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.joaquin.curso.springboot.error.springbooterror.models.domain.User;

@Repository
public class UserRepository {
    
    private List<User> users;

    public UserRepository(){
        this.users = new ArrayList<>();
        users.add(new User("Joaquin", "Mayor", 1L));
        users.add(new User("Dario", "Mayor", 2L));
        users.add(new User("Rosa", "Otero", 3L));
        users.add(new User("Ignacio", "Tosini", 4L));
        users.add(new User("Facundo", "Milano", 5L));
    }
    public List<User> findAll(){
        
        return users;
    }

    public User findById(Long id){
        return  users.stream().filter(p->p.getId().equals(id)).findFirst().orElse(null);
    }
}

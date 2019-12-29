package com.cpe.backend.RegisterPatient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;

import com.cpe.backend.RegisterPatient.entity.User;
import com.cpe.backend.RegisterPatient.repository.UserRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {

    @Autowired
    private final UserRepository UserRepository;

    public UserController(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    @GetMapping("/User")
    public Collection<User> Users() {
        return UserRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/User/{id}")
    public Optional<User> Users(@PathVariable String id) {
        Optional<User> User = UserRepository.findById(id);
        return User;
    }
    @RequestMapping(value = "/User/login",method = RequestMethod.POST)
    public String Login(@RequestBody Map<String, String> body){
        User UserU = UserRepository.findById(body.get("username")).get();
        String U = UserU.getPassword();
        String P = body.get("password");
        System.out.println(U);
        System.out.println(P);
        if(U.equals(P)){
            return body.get("username");
        }
        return "Error not Found username or password";
    }



}
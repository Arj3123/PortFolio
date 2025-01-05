package com.project.Portfolio.Controller;

import com.project.Portfolio.DTO.UserDTO;
import com.project.Portfolio.Entity.UserEntity;
import com.project.Portfolio.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> CreateUser(@RequestBody UserDTO user) {
        try{
            UserEntity user1= new UserEntity();
            user1.setUsername(user.getUsername());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            userService.saveUser(user1);
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping(path="/{id}")
    public ResponseEntity<?> FindUser(@PathVariable long id) {
        Optional<UserEntity> user = userService.getUser(id);
        if(user!=null) {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<UserEntity> list=userService.getAll();
        if(list!=null&&!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

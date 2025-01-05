package com.project.Portfolio.Service;

import com.project.Portfolio.Entity.UserEntity;
import com.project.Portfolio.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserEntity user){
        userRepository.save(user);
    }
    public Optional<UserEntity> getUser(Long id){
        return userRepository.findById(id);
    }

    public List<UserEntity> getAll() {
        return StreamSupport.stream(userRepository
                        .findAll()
                        .spliterator(),false)
                .collect(Collectors.toList());
    }
}

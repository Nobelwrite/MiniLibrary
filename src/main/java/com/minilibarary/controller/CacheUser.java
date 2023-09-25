package com.minilibarary.controller;

import com.minilibarary.model.Book;
import com.minilibarary.model.User;
import com.minilibarary.repository.BookRepository;
import com.minilibarary.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CacheUser {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;


    @CacheEvict(cacheNames = "users", key = "#userId")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @CacheEvict(cacheNames = "delete", key = "#userId")
    public ResponseEntity<User> deleteUser(@PathVariable User user) {
        userRepository.delete(user);
        return ResponseEntity.ok(user);
    }

    @CachePut(cacheNames = "updateUser", key = "#userId")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        User users1 = userRepository.getByUserId(userId);
        if (users1 != null) {
            if (user.getFullName() != null) {
                users1.setFullName(user.getFullName());

            }
            if (user.getAddress() != null) {
                users1.setAddress(user.getAddress());
            }
            if (user.getAge() != null) {
                users1.setAge(user.getAge());
            }
            if (user.getEmail() != null) {
                users1.setEmail(user.getEmail());
            }
            userRepository.save(users1);
        }
        return ResponseEntity.ok(users1);
    }

    @Cacheable(cacheNames = "updateUser", key = "#userId")
    public User findUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Can't find this user"));
    }

    @Cacheable(cacheNames = "findUserByFullName", key = "#userId")
    public User findUserByFullName(@PathVariable String fullName) {
        return userRepository.findUserByFullName(fullName).orElseThrow(() -> new ResourceNotFoundException("Can't find this user"));
    }

    @Cacheable(cacheNames = "findUserByEmail", key = "#email")
    public User findUserByEmail(@PathVariable String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Can't find this user"));
    }

    @Cacheable(cacheNames = "users", key = "#userId")
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }
}



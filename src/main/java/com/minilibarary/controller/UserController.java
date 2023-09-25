package com.minilibarary.controller;

import com.minilibarary.model.User;
import com.minilibarary.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")

public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private CacheUser cacheUser;

    @PostMapping("/users/{addUser}")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        //return userRepository.save(users);
        if (bindingResult.hasErrors()) {
            ResponseEntity.ok("User not created");
        }
        return cacheUser.addUser(user);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String>deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("Use has been deleted");
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User>updateUser( @PathVariable Long id, @RequestBody User users){
        cacheUser.updateUser(id, users);
        return ResponseEntity.ok(users);
    }
    @GetMapping("/users/{findById}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cacheUser.findUserById(id));
    }
    @GetMapping("/users/{fullName}")
    public ResponseEntity<User> findUserByFullName(@PathVariable String fullName) {
        return ResponseEntity.ok(cacheUser.findUserByFullName(fullName));
    }
    @GetMapping("/users/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(cacheUser.findUserByEmail(email));
    }

}

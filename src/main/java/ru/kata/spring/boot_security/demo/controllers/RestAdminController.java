package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import java.util.List;


@RestController
@RequestMapping("/adminApi")
public class RestAdminController {

    private final UserService userService;

    @Autowired
    public RestAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.findAllUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("user/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
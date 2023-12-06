package com.osiki.testproject.controller;

import com.osiki.testproject.model.Users;
import com.osiki.testproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("users")
    public Users saveUser(@RequestBody Users users){
        System.out.println("recieved data " + users.toString());
        return userService.saveUser(users);
    }

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable Long id){
        Users users = null;
       users =  userService.getUserById(id);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUsers(@PathVariable Long id, @RequestBody Users users){
        users = userService.updateUsers(id, users);

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
        boolean deleted = false;
        deleted = userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);

        return ResponseEntity.ok(response);
    }

}

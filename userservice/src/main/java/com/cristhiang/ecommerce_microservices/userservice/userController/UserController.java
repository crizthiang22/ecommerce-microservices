package com.cristhiang.ecommerce_microservices.userservice.userController;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cristhiang.ecommerce_microservices.userservice.userDTO.Request.UserRequestDTO;
import com.cristhiang.ecommerce_microservices.userservice.userDTO.Response.UserResponseDTO;
import com.cristhiang.ecommerce_microservices.userservice.userService.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UserController {
    private final UserService userService;
    
    public UserController (UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
            return userService.getUserById(id);
        }
    @GetMapping
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }
    
    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable ("id") Long userId, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateUser(userId, userRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
    }


}
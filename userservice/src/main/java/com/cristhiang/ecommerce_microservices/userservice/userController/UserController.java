package com.cristhiang.ecommerce_microservices.userservice.userController;

import com.cristhiang.ecommerce_microservices.userservice.userModel.User;
import com.cristhiang.ecommerce_microservices.userservice.userRepository.UserRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RestController
@RequestMapping("apiv1/users")
class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }
}


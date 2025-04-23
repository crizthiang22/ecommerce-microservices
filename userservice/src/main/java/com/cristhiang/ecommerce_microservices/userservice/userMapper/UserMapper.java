package com.cristhiang.ecommerce_microservices.userservice.userMapper;

import org.springframework.stereotype.Component;

import com.cristhiang.ecommerce_microservices.userservice.userDTO.Request.UserRequestDTO;
import com.cristhiang.ecommerce_microservices.userservice.userDTO.Response.UserResponseDTO;

import com.cristhiang.ecommerce_microservices.userservice.userModel.User;


//Mapper: Transfers data between layers (DTO ↔ entity) but does not handle security.


@Component
public class UserMapper {

    public User mapToUser(UserRequestDTO request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        return user;
    }

    public UserResponseDTO mapToUserResponse(User user) {
        return UserResponseDTO.builder()
        .id(user.getId())
        .username(user.getUsername())
        .email(user.getEmail())
        .build();
    }

    public void updateUserFromRequest(UserRequestDTO request, User user) {
        if(request.getUsername() != null){
            user.setUsername(request.getUsername());
        }
        if(request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if(request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(request.getPassword());
        }
    }
}
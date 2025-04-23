package com.cristhiang.ecommerce_microservices.userservice.userService;

import com.cristhiang.ecommerce_microservices.userservice.customExceptions.EmailAlreadyExistsException;
import com.cristhiang.ecommerce_microservices.userservice.customExceptions.UserNotFoundException;
import com.cristhiang.ecommerce_microservices.userservice.userDTO.Request.UserRequestDTO;
import com.cristhiang.ecommerce_microservices.userservice.userDTO.Response.UserResponseDTO;
import com.cristhiang.ecommerce_microservices.userservice.userMapper.UserMapper;
import com.cristhiang.ecommerce_microservices.userservice.userModel.User;
import com.cristhiang.ecommerce_microservices.userservice.userRepository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        validateEmailUniqueness(userRequest.getEmail());
        
        User newUser = userMapper.mapToUser(userRequest);
        User savedUser = userRepository.save(newUser);
        return userMapper.mapToUserResponse(savedUser);
    }

    public UserResponseDTO getUserById(Long userId) {
        User user = findUserOrThrow(userId);
        return userMapper.mapToUserResponse(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequest) {
        User existingUser = findUserOrThrow(userId);
        
        if (!existingUser.getEmail().equals(userRequest.getEmail())) {
            validateEmailUniqueness(userRequest.getEmail());
        }
        
        userMapper.updateUserFromRequest(userRequest, existingUser);
        User updatedUser = userRepository.save(existingUser);
        return userMapper.mapToUserResponse(updatedUser);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Helper methods 
    private User findUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    private void validateEmailUniqueness(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email already registered: " + email);
        }
    }
}
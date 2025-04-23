package com.cristhiang.ecommerce_microservices.userservice.userRepository;

import com.cristhiang.ecommerce_microservices.userservice.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
    


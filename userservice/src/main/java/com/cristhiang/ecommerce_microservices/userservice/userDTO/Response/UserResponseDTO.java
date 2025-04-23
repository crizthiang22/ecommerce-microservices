package com.cristhiang.ecommerce_microservices.userservice.userDTO.Response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private long id;
    private String username;
    private String email;
    
    
}

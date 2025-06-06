package com.cristhiang.ecommerce_microservices.userservice.userModel;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")

	private String username;

	@Column(name = "email")

	private String email;

	@Column (name = "password")
	private String password;
}

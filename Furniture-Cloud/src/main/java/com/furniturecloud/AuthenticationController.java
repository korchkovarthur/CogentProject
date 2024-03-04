package com.furniturecloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furniturecloud.datalayer.User;
import com.furniturecloud.security.utils.LoginResponseDTO;
import com.furniturecloud.security.utils.RegistrationDTO;
import com.furniturecloud.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authService;
	
	@PostMapping("/reg/{password}")
	public LoginResponseDTO regUser(@RequestBody User u, @PathVariable String password ) {
		return authService.registerUser(u, password);
		
	}
	@PostMapping("/login")
	public LoginResponseDTO loginUser(@RequestBody RegistrationDTO dto) {
		return authService.loginUser(dto.getEmail(), dto.getPassword());
		
	}
}

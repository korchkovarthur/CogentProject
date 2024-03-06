package com.furniturecloud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authService;
	
	@PostMapping("/reg/{password}")
	public ResponseEntity<?> regUser(@Valid@RequestBody User u, @PathVariable String password, BindingResult br) {
		System.out.println(u.getEmail());
		if(!br.hasErrors()) {
			return ResponseEntity.status(HttpStatus.OK).body(authService.registerUser(u, password));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
		
	}
	@PostMapping("/login")
	public LoginResponseDTO loginUser(@RequestBody RegistrationDTO dto) {
		System.out.println("IN LOGIN");
		return authService.loginUser(dto.getEmail(), dto.getPassword());
		
	}
}

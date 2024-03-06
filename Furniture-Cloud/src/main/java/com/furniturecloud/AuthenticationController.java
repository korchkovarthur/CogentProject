package com.furniturecloud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furniturecloud.datalayer.DAO;
import com.furniturecloud.datalayer.Product;
import com.furniturecloud.datalayer.User;
import com.furniturecloud.security.utils.LoginResponseDTO;
import com.furniturecloud.security.utils.RegistrationDTO;
import com.furniturecloud.services.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/")
@CrossOrigin("*")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authService;

	@Autowired
	private DAO<Product, Long> product;
	@PostMapping("reg/{password}")
	public ResponseEntity<?> regUser(@Valid@RequestBody User u, @PathVariable String password, BindingResult br) {
		System.out.println(u.getEmail());
		if(!br.hasErrors()) {
			return ResponseEntity.status(HttpStatus.OK).body(authService.registerUser(u, password));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
		
	}
	@PostMapping("login")
	public LoginResponseDTO loginUser(@RequestBody RegistrationDTO dto) {
		System.out.println("IN LOGIN");
		return authService.loginUser(dto.getEmail(), dto.getPassword());
		
	}
	@GetMapping("product/getAll/{param1}/{param2}/{param3}")
	public ResponseEntity<?> getAllProducts(@PathVariable("param1") String param1,
			@PathVariable("param2") String param2, @PathVariable("param3") String param3) {	
		param3=param3.toLowerCase();
		if(!(param1.equals("Category")||param1.equals("none")))
			return ResponseEntity.status(HttpStatus.OK).body("Invalid Query Parameter");
		List<Product> l=product.getAll(param1,param2);
		if(param3.equals("name")) {
			l.sort((a,b)->(a.getName().toLowerCase()).compareTo(a.getName().toLowerCase()));
		}
		else if(param3.equals("price")){
			l.sort((a,b)->(int)(a.getPrice()-b.getPrice()));	
		}
		else {
			
		}
		System.out.println(l);
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}
}

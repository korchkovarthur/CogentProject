package com.furniturecloud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.furniturecloud.datalayer.DAO;
import com.furniturecloud.datalayer.User;

import jakarta.validation.Valid;

public class UserController {
	@Autowired
	private DAO<User, String> user;
	@PostMapping("/user/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody User t, BindingResult br) {
		if(!br.hasErrors()) {
			if(user.get(t.getEmail())==null) {
			user.create(t);
			return ResponseEntity.status(HttpStatus.OK).body(user.get(t.getEmail()));
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(user.get(t.getEmail()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
	}	
	
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<?> deleteUser(@Valid @PathVariable String id) {
		user.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}
	
	@PutMapping("/user/update/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody  User t, @PathVariable String id,  BindingResult br) {  
		
		if(!br.hasErrors()) {
			t.setEmail(id);
			user.update(t);
			return ResponseEntity.status(HttpStatus.OK).body("Updated");
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
	}
	
	@GetMapping("/user/get/{email}")
	public ResponseEntity<?> getUser(@Valid @PathVariable String email) {
		User t =user.get(email);
		if(t!=null)
			return ResponseEntity.status(HttpStatus.OK).body(t);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email id");
	}
	
	@GetMapping("/user/getAll")
	public ResponseEntity<?> getAllUsers() {	
		List<User> l=user.getAll();
		System.out.println(l);
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}
}

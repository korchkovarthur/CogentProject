package com.furniturecloud;



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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.furniturecloud.datalayer.DAO;
import com.furniturecloud.datalayer.Product;
import com.furniturecloud.datalayer.User;

import jakarta.validation.Valid;
@RestController
public class AdminController{
	@Autowired
	private DAO<User, String> user;
	@Autowired
	private DAO<Product, Long> product;
	//Missing an annotation to resolve request to User object  
	//Need to decide on return values
	
	
	//Product
	@PostMapping("/product/create")
	public ResponseEntity<?> createProduct(@Valid @RequestBody  Product prod, BindingResult br) {
		if(!br.hasErrors()) {
			if(product.get(prod.getSKU())==null) {
				product.create(prod);
				return ResponseEntity.status(HttpStatus.OK).body(product.get(prod.getSKU()));
			}
			return false;		
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
			

	}
	
	@DeleteMapping("/product/delete")
	public ResponseEntity<?> deleteProduct(@Valid @RequestBody Product prod) {
		product.delete(prod);
	}
	
	@PutMapping("/product/update/")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody Product prod) {
		product.update(prod);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProduct(@Valid @PathVariable Long id) {
		product.get(id);
	}
	
	
	//User
	@PostMapping("/user/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody User t) {
		user.create(t);
	}
	@DeleteMapping("/user/delete")
	public ResponseEntity<?> deleteUser(@Valid @RequestBody User t) {
		user.delete(t);
	}
	@PutMapping("/user/update/")
	public ResponseEntity<?> updateUser(@Valid @RequestBody  User t) {  
		user.update(t);
	}
	@GetMapping("/user/{email}")
	public ResponseEntity<?> getUser(@Valid@PathVariable String email) {
		user.get(email);
		return 
	}
	<T> ResponseEntity<T> evaluateRequest(T item, HttpStatus success, HttpStatus fail ){
		if(item!=null) {
			return ResponseEntity.status(success).body(item);	
		}
		return ResponseEntity.status(fail).body(item);		
	}
	
}

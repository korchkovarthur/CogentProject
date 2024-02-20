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
import org.springframework.web.bind.annotation.RestController;


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
			product.create(prod);
			return ResponseEntity.status(HttpStatus.OK).body(product.get(prod.getSKU()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());			
	}
	
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<?> deleteProduct( @PathVariable Long sku) {
		product.delete(sku);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}
	
	@PutMapping("/product/update/{id}")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody Product prod,
			@PathVariable Long id, BindingResult br) {
		if(!br.hasErrors()) {
			prod.setSKU(id);
			product.update(prod);
			return ResponseEntity.status(HttpStatus.OK).body("Updated");
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProduct(@Valid @PathVariable Long id) {	
		Product p =product.get(id);
		if(p!=null)
			return ResponseEntity.status(HttpStatus.OK).body(p);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid SKU");
	}
	@GetMapping("/product/getAll/{param1}/{param2}")
	public ResponseEntity<?> getAllProducts(@PathVariable("param1") String param1, @PathVariable("param2") String param2) {	
		if(!(param1.equals("Category")||param1.equals("none")))
			return ResponseEntity.status(HttpStatus.OK).body("Invalid Query Parameter");
		List<Product> l=product.getAll(param1,param2);
		System.out.println(l);
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}
	
	
	
	
	//User
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

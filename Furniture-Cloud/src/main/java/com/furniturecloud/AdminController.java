package com.furniturecloud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furniturecloud.datalayer.Cart;
import com.furniturecloud.datalayer.DAO;
import com.furniturecloud.datalayer.Product;
import com.furniturecloud.datalayer.User;
import com.furniturecloud.security.utils.CartDTO;
import com.furniturecloud.security.utils.LoginDTO;
import com.furniturecloud.security.utils.LoginResponseDTO;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
	@Autowired
	private DAO<User, Integer> user;
	@Autowired
	private DAO<Product, Long> product;

	//Start-----User - Admin Access
	@DeleteMapping("user/delete/{id}")
	public ResponseEntity<?> deleteUser(@Valid @PathVariable Integer id) {
		user.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}
	
	@PutMapping("user/update/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody  User t, @PathVariable Integer id,  BindingResult br) {  
		
		if(!br.hasErrors()) {
			t.setUser_id(id);
			user.update(t);
			return ResponseEntity.status(HttpStatus.OK).body("Updated");
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
	}
	
	@GetMapping("user/get/{id}")
	public ResponseEntity<?> getUser(@Valid @PathVariable Integer id) {
		User t =user.get(id);
		if(t!=null)
			return ResponseEntity.status(HttpStatus.OK).body(t);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id");
	}
		
	@GetMapping("user/getAll")
	public ResponseEntity<?> getAllUsers() {	
		List<User> l=user.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}
	//User-----Product - Admin Access
	
	
	//Start-----Product - Admin Access
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
	//End-----Product - Admin Access
	
	//Validation
	@GetMapping
	public ResponseEntity<?> getMethodName() {
		System.out.println("IN ADMIN ACCESS");
		return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(null,"Admin Access"));
	}


	
}

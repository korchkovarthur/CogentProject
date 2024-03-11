package com.furniturecloud;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furniturecloud.datalayer.DAO;
import com.furniturecloud.datalayer.Product;
import com.furniturecloud.datalayer.User;
import com.furniturecloud.security.utils.LoginResponseDTO;
import com.furniturecloud.services.AuthenticationService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
	@Autowired
	private DAO<User, Integer> user;
	@Autowired
	private DAO<Product, Long> product;
	@Autowired
	private AuthenticationService authService;

	//Start-----User - Admin Access
	@DeleteMapping("user/delete/{id}")
	public ResponseEntity<?> deleteUser(@Valid @PathVariable Integer id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(authService.deleteUser(user.get(id)));
	}
	
	@PutMapping("user/update/{password}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody  User t, @PathVariable String password,  BindingResult br) {  
		
		if(!br.hasErrors()) {
			return ResponseEntity.status(HttpStatus.OK).body(authService.updateUser(t, password));
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
	}
	
	@GetMapping("user/get/{id}")
	public ResponseEntity<?> getUser(@Valid @PathVariable Integer id) {
		User t =user.get(id);
		if(t!=null)
			return ResponseEntity.status(HttpStatus.OK).body(t);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponseDTO(null,"Invalid ID"));
	}
		
	@GetMapping("user/getAll")
	public ResponseEntity<?> getAllUsers() {	
		List<User> l=user.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}
	@PostMapping("user/create/{password}")
	public ResponseEntity<?> regUser(@Valid@RequestBody User u, @PathVariable String password, BindingResult br) {
		System.out.println(u.getEmail());
		if(!br.hasErrors()) {
			return ResponseEntity.status(HttpStatus.OK).body(authService.registerUser(u, password));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
		
	}
	//End-----User - Admin Access
	
	
	//Start-----Product - Admin Access
	@PostMapping("/product/create")
	public ResponseEntity<?> createProduct(@Valid @RequestBody  Product prod, BindingResult br) {
		if(!br.hasErrors()) {
			Product p = new Product(prod.getName(), prod.getDescr(), prod.getCategory(), prod.getStock(), prod.getPrice());
			product.create(p);
			return ResponseEntity.status(HttpStatus.OK).body(product.get(prod.getSKU()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());			
	}
	
	@DeleteMapping("/product/delete/{sku}")
	public ResponseEntity<?> deleteProduct( @PathVariable Long sku) {
		System.out.println("In product delete");
		product.delete(sku);
		return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(null,"Deleted"));
	}
	
	@PutMapping("/product/update")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody Product prod, BindingResult br) {
		if(!br.hasErrors()) {
			product.update(prod);
			return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(null,"Updated"));
			
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

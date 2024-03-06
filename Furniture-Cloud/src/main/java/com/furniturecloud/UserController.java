package com.furniturecloud;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furniturecloud.datalayer.Cart;
import com.furniturecloud.datalayer.DAO;
import com.furniturecloud.datalayer.Product;
import com.furniturecloud.datalayer.User;
import com.furniturecloud.datalayer.UserRepo;
import com.furniturecloud.security.utils.CartDTO;
import com.furniturecloud.security.utils.LoginResponseDTO;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserRepo user2;
	@Autowired
	private DAO<User, Integer> user;
	@Autowired
	private DAO<Product, Long> product;
	
	
	//Start-----Product - User Access
	
	@GetMapping("product/get/{id}")
	public ResponseEntity<?> getProduct(@Valid @PathVariable Long id) {	
		Product p =product.get(id);
		if(p!=null)
			return ResponseEntity.status(HttpStatus.OK).body(p);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid SKU");
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
	@PostMapping("/verifyCart/{cart}")
	public CartDTO verifyCart(@PathVariable String cart) {
		Cart ob = new Cart(cart);
		String unavaliable=ob.findUnavailable();
		return new CartDTO(cart, unavaliable);
	}
	//END-----Product - User Access
	
	//Start-----User - User Access
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody User t, BindingResult br) {
		if(!br.hasErrors()) {
			if(user.get(t.getUser_id())==null) {
			user.create(t);
			return ResponseEntity.status(HttpStatus.OK).body(user.get(t.getUser_id()));
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(user.get(t.getUser_id()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
	}	
    
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody User t) {
		//TODO: process PUT request
		String email = userDetails.getUsername();
		Optional<User> u = user2.findByEmail(t.getEmail());
		if(u.isEmpty()) {
		int id= user2.findByEmail(email).get().getUser_id();
		t.setUser_id(id);
		user.update(t);
		return ResponseEntity.status(HttpStatus.OK).body("Success");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed");
	}
	//End-----User - User Access
	
	//Checking validity
	@GetMapping
	public ResponseEntity<?> checkAccess(@AuthenticationPrincipal UserDetails userDetails) {
		User u = user2.findByEmail(userDetails.getUsername()).get();
		return  ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(null,"User Access"));
	}
}

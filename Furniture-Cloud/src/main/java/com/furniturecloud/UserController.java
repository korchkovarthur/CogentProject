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

	@PostMapping("/verifyCart/{cart}")
	public CartDTO verifyCart(@PathVariable String cart) {
		Cart ob = new Cart(cart);
		String unavaliable=ob.findUnavailable();
		return new CartDTO(cart, unavaliable);
	}
	//END-----Product - User Access
	
	//Start-----User - User Access
	
    
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody User t) {
		//TODO: process PUT request

//		Optional<User> u = user2.findByEmail(t.getEmail());
		User u =user.get(t.getUser_id());
		u.setCartData(t.getCartData());
		u.setWishListData(t.getWishListData());
		user.update(u);
		return ResponseEntity.status(HttpStatus.OK).body(u);

	}
	@GetMapping("/get")
	public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetails userDetails) {
		//TODO: process PUT request
		String email = userDetails.getUsername();
		Optional<User> u = user2.findByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body(u.get());
	}
	//End-----User - User Access
	
	//Checking validity
	@GetMapping
	public ResponseEntity<?> checkAccess(@AuthenticationPrincipal UserDetails userDetails) {
//		User u = user2.findByEmail(userDetails.getUsername()).get();
		return  ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(null,"User Access"));
	}
}

package com.furniturecloud;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furniturecloud.datalayer.DAO;
import com.furniturecloud.datalayer.Orders;
import com.furniturecloud.datalayer.UserRepo;
import com.furniturecloud.security.utils.LoginResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {
	@Autowired
	private DAO<Orders, UUID> Orders;
	@Autowired
	private UserRepo user;
	// Missing an annotation to resolve request to User object
	// Need to decide on return values

	// Order
	@GetMapping("/")
	public String getMethodName() {
		return "Orders";
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createOrder(@Valid @RequestBody Orders o,BindingResult br) {
		if (!br.hasErrors()) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(auth.getName()+" "+auth.getPrincipal()+" "+auth.getDetails());
			Orders or = new Orders(o.getCart(),user.findByEmail(auth.getName()).get());
			Orders.create(or);
			return ResponseEntity.status(HttpStatus.OK).body(or);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
	}

//	@DeleteMapping("/Order/delete/{id}")
//	public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
//		Order.delete(id);
//		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
//	}

//	@PutMapping("/Order/update/{id}")
//	public ResponseEntity<?> updateOrder(@Valid @RequestBody Order prod, @PathVariable Long id, BindingResult br) {
//		if (!br.hasErrors()) {
//			prod.setid(id);
//			Order.update(prod);
//			return ResponseEntity.status(HttpStatus.OK).body("Updated");
//
//		}
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
//	}

	@GetMapping("/order/get/{uuid}")
	public ResponseEntity<?> getOrder(@Valid @PathVariable UUID uuid) {
		Orders o = Orders.get(uuid);
		if (o != null)
			return ResponseEntity.status(HttpStatus.OK).body(o);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID");
	}


	@GetMapping("/getAll/{param1}/{param2}")
	public ResponseEntity<?> getAllOrders(@PathVariable("param1") String param1,
			@PathVariable("param2") String param2) {
		if (!(param1.equals("User") || param1.equals("none")))
			return ResponseEntity.status(HttpStatus.OK).body("Invalid Query Parameter");
		List<Orders> l = Orders.getAll(param1, param2);
		System.out.println(l);
		System.out.println(l);
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}

}

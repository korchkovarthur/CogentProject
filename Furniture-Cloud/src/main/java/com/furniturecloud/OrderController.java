package com.furniturecloud;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.furniturecloud.datalayer.DAO;
import com.furniturecloud.datalayer.Orders;

import jakarta.validation.Valid;

@RestController
public class OrderController {
	@Autowired
	private DAO<Orders, UUID> Orders;
	// Missing an annotation to resolve request to User object
	// Need to decide on return values

	// Order
	@GetMapping("/orders")
	public String getMethodName() {
		return "Orders";
	}
	
	@PostMapping("/order/create")
	public ResponseEntity<?> createOrder(@Valid @RequestBody Orders prod, BindingResult br) {
		if (!br.hasErrors()) {
			Orders.create(prod);
			return ResponseEntity.status(HttpStatus.OK).body(Orders.get(prod.getId()));
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

	@GetMapping("/order/getAll/{param1}/{param2}")
	public ResponseEntity<?> getAllOrders(@PathVariable("param1") String param1,
			@PathVariable("param2") String param2) {
		if (!(param1.equals("id") || param1.equals("none")))
			return ResponseEntity.status(HttpStatus.OK).body("Invalid Query Parameter");
		List<Orders> l = Orders.getAll(param1, param2);
		System.out.println(l);
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}

}

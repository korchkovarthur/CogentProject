package com.furniturecloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.furniturecloud.datalayer.Cart;
import com.furniturecloud.datalayer.DAO;
import com.furniturecloud.datalayer.Product;
import com.furniturecloud.security.utils.CartDTO;

import jakarta.validation.Valid;
//@RestController
//@RequestMapping("/product")
public class ProductController{
	
	@Autowired
	private DAO<Product, Long> product;
	
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@Valid @RequestBody  Product prod, BindingResult br) {
		if(!br.hasErrors()) {
			product.create(prod);
			return ResponseEntity.status(HttpStatus.OK).body(product.get(prod.getSKU()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());			
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct( @PathVariable Long sku) {
		product.delete(sku);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody Product prod,
			@PathVariable Long id, BindingResult br) {
		if(!br.hasErrors()) {
			prod.setSKU(id);
			product.update(prod);
			return ResponseEntity.status(HttpStatus.OK).body("Updated");
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getAllErrors());
	}

	@PostMapping("/verifyCart/{cart}")
	public Object postMethodName(@PathVariable String cart) {
		Cart ob = new Cart(cart);
		String unavaliable=ob.findUnavailable();
		return new CartDTO(cart, unavaliable);
	}
	
}

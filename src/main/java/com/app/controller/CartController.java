package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.ICartDao;
import com.app.dto.ResponseDTO;
import com.app.pojos.Cart;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins ="http://localhost:4200")
public class CartController {
	
	@Autowired
	private ICartDao dao;
	
	//REST API to find all the cart items
	@GetMapping
	public ResponseEntity<?> getAllCartDetails()
	{
		List<Cart> cart = dao.findAll();		
		return new ResponseEntity<>(new ResponseDTO("success","Cart Details fetched.",cart),HttpStatus.OK);
	}
	
	//REST API to find the cart items by menu_id
	@GetMapping("/{menuCartId}")
	public ResponseEntity<?> getMenuCartDetails(@PathVariable int menuCartId) {
		System.out.println("in get Tech dtls " + menuCartId);
		Optional<Cart> optional = dao.findById(menuCartId);
		if (optional.isPresent())
			return new ResponseEntity<>(new ResponseDTO("success","Details Found",optional.get()),HttpStatus.OK);
		// invalid id
		return new ResponseEntity<>(new ResponseDTO("error","Menu id not Found",null),HttpStatus.NOT_FOUND);

	}
	
	@PostMapping
	public ResponseEntity<?> addMenuInCartDetails(@RequestBody Cart c) {
		System.out.println("in add MenuCart" + c);
		return new ResponseEntity<>(new ResponseDTO("success","New Items added in cart.",dao.save(c)), HttpStatus.CREATED);
	}
	
	@PutMapping("/{cartId}")
	public ResponseEntity<?> updateCartDetails(@PathVariable int cartId, @RequestBody Cart cart) {
		System.out.println("in update cart" + cartId+ " " + cart);
		Optional<Cart> optional =dao.findById(cartId);
		if (optional.isPresent()) {
			Cart existingItems = optional.get();// DETACHED
			System.out.println("existing details " + existingItems);
			existingItems.setMenu_id(cart.getMenu_id());			
			existingItems.setPrice(cart.getPrice());
			existingItems.setQuantity(cart.getQuantity());
			existingItems.setTotal_amount(cart.getTotal_amount());
			dao.save(existingItems);
			return new ResponseEntity<>(new ResponseDTO("success","On request Cart Updated",null), HttpStatus.OK);
			
		} else
			return new ResponseEntity<>(new ResponseDTO("error","Cart Updatation failed",null), HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{cartId}")
	public ResponseEntity<?> deleteCartDetails(@PathVariable  int cartId) {
		System.out.println("in delete cart items " + cartId);
		// check if user exists
		Optional<Cart> optional = dao.findById(cartId);
		if (optional.isPresent()) {
			dao.deleteById(cartId);
			return new ResponseEntity<>(new ResponseDTO("success","Cart Item deleted with ID " + cartId,null), HttpStatus.OK);
		} else
			 //throw new ResourceNotFoundException("Cart ID Invalid : Item deletion failed.");
			return new ResponseEntity<>(new ResponseDTO("error","Cart Item deletion failed" ,null), HttpStatus.NOT_ACCEPTABLE);
	}

	
	
	
	
	
	
}

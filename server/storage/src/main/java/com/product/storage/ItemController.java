package com.product.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ItemController {

	private final ItemService itemService;

	@Autowired
	public ItemController(ItemService itemService) {
		super();
		this.itemService = itemService;
	}

	@GetMapping("/items")
	public List<Item> getItems() {
		return itemService.getItems();
	}

	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItems(@PathVariable Integer id) {
		try {
			Item existItem = itemService.getItemById(id);
			return new ResponseEntity<Item>(existItem, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/items")
	public Map<String,String> addItem(@RequestBody Item item ){
		Map<String,String> map = new HashMap<>();
		try{
			itemService.addItem(item);		
			map.put("Status", "Sucess");
		}
		catch(Exception ex ){
			map.put("Status", "Failure");
			map.put("Message", "Error occured file adding error, please contanct support");
		}
		
		return map;
	}

	@PutMapping("/items/{id}")
	public ResponseEntity<?> updateItem(@RequestBody Item item, @PathVariable Integer id) {
		try {
			Item existItem = itemService.getItemById(id);
			itemService.updateItem(id, existItem);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/items/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable Integer id) {
		try {
			Item existItem = itemService.getItemById(id);
			itemService.deleteItem(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}

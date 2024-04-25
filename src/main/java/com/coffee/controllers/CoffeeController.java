package com.coffee.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.coffee.models.Menu;
import com.coffee.repositories.CoffeeRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class CoffeeController {
	
	@Autowired
	CoffeeRepository coffeeRepository;
	
	@PostMapping("/menu")
	public String createNewMenu(@RequestBody Menu menu) {
		coffeeRepository.save(menu);
		return "Menu added ";
	}
	
	@GetMapping("menu")
	public ResponseEntity<List<Menu>> getAllMenu(){
		List<Menu> menuList = new ArrayList<>();
		coffeeRepository.findAll().forEach(menuList::add);
		return new ResponseEntity<List<Menu>>(menuList,HttpStatus.OK);		
	}

	
	@GetMapping("/menu/{id}")
	public ResponseEntity<Menu> getMenuById(@PathVariable int id){
		Optional<Menu> menuId = coffeeRepository.findById(id);
		if (menuId.isPresent()) {
			return new ResponseEntity<Menu>(menuId.get(),HttpStatus.FOUND);
		}else {
			return new ResponseEntity<Menu>(HttpStatus.NOT_FOUND);
		}
	}
	

	@PutMapping("/menu/{id}")
	public String updateMenuById(@PathVariable int id, @RequestBody Menu menu) {
		Optional<Menu> menuId = coffeeRepository.findById(id);
		if (menuId.isPresent()) {
			Menu existMenu = menuId.get();
			existMenu.setName(menu.getName());
			existMenu.setPrice(menu.getPrice());
			existMenu.setDescription(menu.getDescription());
			coffeeRepository.save(existMenu);
			return "Menu with id" + id  + " updated";
		}else {
			return "Menu with id" + id + " not found";
		}
	}
	
	
	@DeleteMapping("/menu/{id}")
	public String deleteById(@PathVariable int id) {
		coffeeRepository.deleteById(id);
		return "menu with id " + "deleted successfully";
	}
	
	
	
	
	
	
	
	
	
}

package com.coffee.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coffee.models.MailResponse;
import com.coffee.models.User;
import com.coffee.repositories.UserRepository;


@CrossOrigin("http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	 @Autowired
	 private JavaMailSender javaMailSender;
	
	@PostMapping("/reservetable")
	public String newUser(@RequestBody User user) {
		userRepository.save(user);
		return "user added successfully";
	}
	
	
	@GetMapping("/reservetable")
	public ResponseEntity<List<User>> getAllUser(){
	List<User> userList = new ArrayList<>();
	userRepository.findAll().forEach(userList::add);
	return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
}
	@GetMapping("/reservetable/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id){
		Optional<User> userId = userRepository.findById(id);
		if (userId.isPresent()) {
			return new ResponseEntity<User>(userId.get(),HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("reservetable/{id}")
	public String updateUserById(@PathVariable int id, @RequestBody User user) {
		Optional<User> userId = userRepository.findById(id);
		if (userId.isPresent()) {
			User existUser = userId.get();
			existUser.setName(user.getName());
			existUser.setEmail(user.getEmail());
			existUser.setPhone(user.getPhone());
			existUser.setDate(user.getDate());
			existUser.setTime(user.getTime());
			existUser.setGuests(user.getGuests());
			userRepository.save(existUser);
			return "user with id "+id+" updated";
		}
		else {
			return "user with is "+id+" not found";
		}
	}
	
	
	
	@DeleteMapping("/reservetable/{id}")
	public String deleteById(@PathVariable int id) {
		userRepository.deleteById(id);
		return "user with id "+id+" deleted successfully";
	}
	
	
	
	 @PostMapping
	    public MailResponse submitContactForm(@RequestBody User user) {
	        // Save the form data or perform any required business logic

	        // Send a static email to the user
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(user.getEmail());
	        message.setSubject("Thank you for contacting us!");
	        message.setText("Hello " + user.getName() + ",\n\nThank you for contacting us. We will get back to you soon.");

	        javaMailSender.send(message);

	        // Respond with success
	        return new MailResponse(true, "Email sent successfully");
	
	
	
	 }
}

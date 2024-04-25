package com.coffee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffee.models.MailResponse;
import com.coffee.models.User;

@RestController
@RequestMapping("/api/contact")
public class MailController {
	
	 @Autowired
	  private JavaMailSender javaMailSender;
	 
	 
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

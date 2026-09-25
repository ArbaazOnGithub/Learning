package com.learning.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.backend.dto.ApiResponse;
import com.learning.backend.entity.User;
import com.learning.backend.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/")
public class UserController {
	
	//Dependency Injection for Service
	@Autowired
	private UserService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<ApiResponse<User>> getUser(@PathVariable Long id) {
	    User user =userService.getUser(id);		
	    ApiResponse<User> response = new ApiResponse<>(true, "User found successfully User ID"+id, user);  
	    return  ResponseEntity.ok(response);
	}
	
	@PostMapping("register")
	public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
		User user1= userService.createUser(user);
		ApiResponse<User> response = new ApiResponse<>(true,"New User has been created with the user name "+user1.getName(),user1);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
		 User user1 =userService.updateUser(id, user);
		 ApiResponse<User> response = new ApiResponse<>(true,"User wit ID "+id+" Has been Updated",user1);
		 return ResponseEntity.ok(response);
	}
	
	@GetMapping("users")
	public ResponseEntity<ApiResponse<List<User>>> getUsers() {
		List<User> users = userService.getUsers();
		ApiResponse<List<User>> response=new ApiResponse<>(true,"All Users",users);
		return ResponseEntity.ok(response);
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		String message=userService.deleteUser(id);
		 ApiResponse<Object> response = new ApiResponse<>(true,message,null);
		return ResponseEntity.ok(response);
	}
}
  
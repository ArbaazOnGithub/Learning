package com.learning.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.backend.entity.User;
import com.learning.backend.exception.ResourceNotFoundException;
import com.learning.backend.repository.UserRepository;

@Service
public class UserService {
	
		//Dependency injection of Repository
		@Autowired
		private UserRepository userRepository; 
		
		//Add user
		public User createUser(User user) {
			return userRepository.save(user);
		}
		
		//Get user
		public User getUser(Long Id) {
				return userRepository.findById(Id)
						.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: "+Id));
		}
		
		//Delete the user
		public String deleteUser(Long Id) {
			User user = userRepository.findById(Id)
					.orElseThrow(()-> new ResourceNotFoundException("User not found with ID: "+Id));
			 userRepository.deleteById(Id);
			 return "User has been deleted";
		}
		
		//Update the User 
		public User updateUser(Long Id,User user) {
			User user1 = userRepository.findById(Id)
					.orElseThrow(()->new ResourceNotFoundException("User not found with ID: "+Id));
			user1.setName(user.getName());
			user1.setEmail(user.getEmail());
			return userRepository.save(user1);
		}
		
		//Get All Users
		public List<User> getUsers(){
		return userRepository.findAll();

		}
}

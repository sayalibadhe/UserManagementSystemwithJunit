package com.poc6.controller;

import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc6.exception.UserException;
import com.poc6.model.ResponseModel;
import com.poc6.model.User;

import com.poc6.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserController")

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired 
	private UserService userService;
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	private ObjectMapper objm=new ObjectMapper();
	
	@ApiOperation(value = "Get all list of users" ,tags = "getAllUser")
	@GetMapping("/getAllUser")
	public ResponseModel getAllUsers() {
		ResponseModel response = new ResponseModel();
		List<User> users = null;

		try {
			users = userService.getAllUsers();
			logger.debug("response  for getAllUsers() method is {}",
					objm.writeValueAsString(users));
			if (!users.isEmpty()) {
				response.setUsers(users);
				response.setSuccess(true);
				response.setStatus("success");
			} else {
				response.setSuccess(false);
				response.setStatus("Failure");
			}
		}
		 catch (JsonProcessingException e) {
			
			logger.error("Error while getAllUsers : " + e.getMessage());
		}
		return response;
	
	

}
	@ApiOperation(value = "Add a user" ,tags = "add")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseModel addEmployee(@RequestBody User u) {
		ResponseModel response = null;
			try {
			logger.debug("input request for addUser() method is {}",
					objm.writeValueAsString(u));
			try {
				response = userService.addUser(u);
				
				response.setSuccess(true);
				response.setStatus("Success");
			} catch (UserException e) {
				
				e.printStackTrace();
			}
			logger.info("response of addUser() method is {}",
					objm.writeValueAsString(response));
		} catch (JsonProcessingException exception) {
			response = new ResponseModel(false, exception.getLocalizedMessage());
			logger.error("Error while Insertion of user : " + response);

		}
		return response;
	}

	@ApiOperation(value = "Get the user details by id" ,tags = "getUser")
	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
	public ResponseModel getUserById(@PathVariable("id") int id)  {
		ResponseModel response = new ResponseModel();
			User user = null;

		user = userService.findUserById(id);
		logger.debug("input request for getUserById() method is {}", id);
		if (user != null) {
			response.setUser(user);
			response.setSuccess(true);
			response.setStatus("Success");
		} else {
			response.setSuccess(false);
			response.setStatus("Failure");
		}
		try {
			logger.info("response  for getUserById() method is {}",
					objm.writeValueAsString(response));
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return response;
	}
	
	@ApiOperation(value = "Get the user details by name" ,tags = "getUserByName")
	@RequestMapping(value = "/getUserByName/{fname}", method = RequestMethod.GET)
	public ResponseModel getUserByfname(@PathVariable("fname") String fname)  {
		ResponseModel response = new ResponseModel();
			User user = null;

		user = userService.findUserByfname(fname);
		logger.debug("input request for getUserByName() method is {}", fname);
		if (user != null) {
			response.setUser(user);
			response.setSuccess(true);
			response.setStatus("Success");
		} else {
			response.setSuccess(false);
			response.setStatus("Failure");
		}
		try {
			logger.info("response  for getUserByName() method is {}",
					objm.writeValueAsString(response));
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return response;
	
}
	
	@ApiOperation(value = "Get the user details by pincode" ,tags = "getUserByPincode")
	@RequestMapping(value = "/getUserByPincode/{pincode}", method = RequestMethod.GET)
	public ResponseModel getUserBypincode(@PathVariable("pincode") String pincode)  {
		ResponseModel response = new ResponseModel();
			List<User> user = null;

		user = userService.findUserBypincode(pincode);
		logger.debug("input request for getUserByPincode() method is {}", pincode);
		if (user != null) {
			response.setUsers(user);
			response.setSuccess(true);
			response.setStatus("Success");
		} else {
			response.setSuccess(false);
			response.setStatus("Failure");
		}
		try {
			logger.info("response  for getUserByPincode() method is {}",
					objm.writeValueAsString(response));
		} catch (JsonProcessingException e) {
		
			e.printStackTrace();
		}
		return response;
	
}
	

	@ApiOperation(value = "Delete the user by id" ,tags = "delete")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseModel deleteUserById(@PathVariable("id") int id) {
		ResponseModel response;
		
			logger.debug("input request for deleteUserById() method is {}", id);
			response = userService.deleteUserById(id);
			if(response.isSuccess()) {
				response.setSuccess(true);
				response.setStatus("Success");
			}
			else {
				response.setSuccess(false);
				response.setStatus("Failure");
			}
			

			try {
				logger.info("response of deleteUserById() method is {}",
						objm.writeValueAsString(response));
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
			}
	
			logger.info("Error while Deleting user : " + response);

		
		return response;
	}
	
	@ApiOperation(value = "Update the user details by entering id" ,tags = "updateUser")
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.PUT)
	public ResponseModel UpdateUser(@PathVariable("id")int id,@Valid @RequestBody User u  )  {
		ResponseModel response = new ResponseModel();
			//User user = null;

		User u1= userService.updateUser(u,id);
		
		logger.debug("input request for UpdateUser() method is {}", id);
		if (u1 != null) {
			response.setUser(u1);
			response.setSuccess(true);
			response.setStatus("Success");
			//UserRepository.save(u1);
			
		} else {
			response.setSuccess(false);
			response.setStatus("Failure");
		}
		try {
			logger.info("response  for UpdateUser() method is {}",
					objm.writeValueAsString(response));
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return response;
	
}
}

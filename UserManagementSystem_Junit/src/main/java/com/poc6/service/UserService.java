package com.poc6.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poc6.exception.UserException;
import com.poc6.repository.UserRepository;
import com.poc6.model.ResponseModel;
import com.poc6.model.User;

@Service
public class UserService {
	@Autowired(required = true)
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	public ResponseModel addUser(User user) throws UserException {
		ResponseModel responseModel;
		if (user != null) {
			userRepository.save(user);
			responseModel = new ResponseModel(true, "User added");

		} else {
			throw new UserException("User not created");

		}
		return responseModel;
	}

	public User findUserByfname(String fname) {

		User user = userRepository.findUserByfname(fname);
		if (user != null) {
			return user;
		} else {
			throw new RuntimeException("User not found");
		}
	}

	public List<User> findUserBypincode(String pincode) {
		List<User> user = userRepository.findUserBypincode(pincode);
		if (user != null) {
			return user;
		} else {
			throw new RuntimeException("User not found");
		}
	}


	public ResponseModel deleteUserById(int id) {

		if (id != 0) {
			userRepository.deleteById(id);
			return new ResponseModel(true, "User Deleted");

		} else {
			return new ResponseModel(false, "User not deleted");
		}

	}

	public User findUserById(int id) {
		User user = userRepository.findUserById(id);
		if (user != null) {
			return user;
		} else {
			throw new RuntimeException("Cannot find user by this id");
		}
	}

	public User updateUser(User user, int id) {
		User u = userRepository.findUserById(id);
		if (u != null) {
			
			u.setFname(user.getFname());
			u.setLname(user.getLname());
			u.setContact(user.getContact());
			u.setEmail(user.getEmail());
			u.setCity(user.getCity());
			u.setCountry(user.getCountry());
			u.setState(user.getState());
			u.setPincode(user.getPincode());
			userRepository.save(u);
			return u;
		} else {
			throw new RuntimeException("No user found with this id");
		}
	}

}

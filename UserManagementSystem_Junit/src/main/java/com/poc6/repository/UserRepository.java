package com.poc6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.poc6.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserByfname(String fname);
	List<User> findUserBypincode(String pincode);

	User findUserById(int id);
}

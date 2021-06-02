package com.cognizant.authorization.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cognizant.authorization.model.User;


public interface UserRepository extends CrudRepository<User, Integer>{

	Optional<User> findByUserName(String userName);
}

package com.ef.interview.repo;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ef.interview.entites.Session;
import com.ef.interview.entites.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
  
	User findByUsername(String username);
	
}

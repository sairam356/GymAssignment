package com.ef.interview.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ef.interview.entites.Trainer;
import com.ef.interview.entites.User;
import com.ef.interview.repo.TrainerRepository;
import com.ef.interview.repo.UserRepository;



@Service
public class GymService {
	

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

   
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    
    public Optional<Trainer> getTrainerById(String trainerId) {
        return trainerRepository.findById(trainerId);
    }


	public void saveUser(List<User> user) {
		userRepository.saveAll(user);
	}
	public void saveTrainers(List<Trainer> user) {
		trainerRepository.saveAll(user);
	}
	
	

}

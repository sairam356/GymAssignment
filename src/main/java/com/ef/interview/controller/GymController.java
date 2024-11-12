package com.ef.interview.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ef.interview.entites.Session;
import com.ef.interview.entites.Trainer;
import com.ef.interview.entites.User;
import com.ef.interview.service.GymService;
import com.ef.interview.service.SessionService;
@RestController
@RequestMapping("/api")
public class GymController {

	    @Autowired
	    private SessionService sessionService;
	    
	    @Autowired
	    GymService  gymService;
	    
	    
	    @GetMapping("/users")
	    public List<User> getAllUsers() {
	        return gymService.getAllUsers();
	    }

	  
	    @GetMapping("/users/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable String id) {
	        Optional<User> user = gymService.getUserById(id);
	        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @GetMapping("/trainers")
	    public List<Trainer> getAllTrainers() {
	        return gymService.getAllTrainers();
	    }

	   
	    @GetMapping("/trainers/{id}")
	    public ResponseEntity<Trainer> getTrainerById(@PathVariable String id) {
	        Optional<Trainer> trainer = gymService.getTrainerById(id);
	        return trainer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @PostMapping("/book")
	    public Session bookSession(@RequestParam String trainerId, @RequestParam String customerId, 
	    	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
	        return sessionService.bookSession(trainerId, customerId, startTime, endTime);
	    }

	    @GetMapping("/search/by-trainer")
	    public List<Session> searchSessionsByTrainer(@RequestParam String trainerId) {
	        return sessionService.searchSessionsByTrainer(trainerId);
	    }

	    @GetMapping("/search/by-customer")
	    public List<Session> searchSessionsByCustomer(@RequestParam String customerId) {
	        return sessionService.searchSessionsByCustomer(customerId);
	    }

	    @GetMapping("/search/by-interval")
	    public List<Session> searchSessionsByTimeInterval(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
	        return sessionService.searchSessionsByTimeInterval(start, end);
	    }

	    @PutMapping("/cancel/{id}")
	    public String cancelSession(@PathVariable String id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime cancelTime) {
	        return sessionService.cancelSession(id, cancelTime);
	    }

	    @PutMapping("/reschedule/{id}")
	    public Session rescheduleSession(@PathVariable String id, 
	    		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newStartTime, 
	    		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newEndTime) {
	        return sessionService.rescheduleSession(id, newStartTime, newEndTime);
	    }
}


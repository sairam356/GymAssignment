package com.ef.interview;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import com.ef.interview.entites.Trainer;
import com.ef.interview.entites.User;
import com.ef.interview.service.GymService;

import lombok.extern.log4j.Log4j2;


@SpringBootApplication
@ComponentScan(basePackages = {"com.ef.interview"})
@EnableAsync
public class InterviewApplication implements CommandLineRunner {
	
	@Autowired
	private RestTemplate restTemplate;
	
	 @Value("${app.users.url}")
	    private String usersUrl;

     @Value("${app.trainers.url}")
	   private String trainersUrl;
     
     @Autowired
 	  GymService gymService;
	

	public static void main(String[] args) {
		
		  SpringApplication.run(InterviewApplication.class, args);

	}
	

    public void run(String... args) {
        callExternalApiForUsers();
        callExternalApiForTrainers();
    }
	
    @Async
    public void callExternalApiForUsers() {
        HttpHeaders headers =createHeaders();
		  
	    HttpEntity<String> entity = new HttpEntity<>(headers);

	    ResponseEntity<List<User>> response = restTemplate.exchange(
	    		usersUrl,
	            HttpMethod.GET,
	            entity,
	            new ParameterizedTypeReference<List<User>>() {}
	    );

	    if (response.getStatusCode().is2xxSuccessful()) {
	        List<User> users =response.getBody();
	        gymService.saveUser(users);
	    } else {
	        throw new RuntimeException("Failed to get data from external API: " + response.getStatusCode());
	    } 
    }

   

	@Async
    private void callExternalApiForTrainers() {
    	     HttpHeaders headers =createHeaders();
		  
		    HttpEntity<String> entity = new HttpEntity<>(headers);

		    ResponseEntity<List<Trainer>> response = restTemplate.exchange(
		    		trainersUrl,
		            HttpMethod.GET,
		            entity,
		            new ParameterizedTypeReference<List<Trainer>>() {}
		    );

		    if (response.getStatusCode().is2xxSuccessful()) {
		        List<Trainer> users =response.getBody();
		        gymService.saveTrainers(users);
		    } else {
		        throw new RuntimeException("Failed to get data from external API: " + response.getStatusCode());
		    }
		
	}

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        return headers;
    }


}

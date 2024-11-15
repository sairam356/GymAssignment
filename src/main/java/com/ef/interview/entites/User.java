package com.ef.interview.entites;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
@JsonInclude(value = Include.NON_NULL)
public class User {
	
	    @Id
	    private Long id;

	    private String name;
	    private String username;
	    private String email;
	    private Address address;
	    private String phone;
	    private String website;
	    private Company company;
	
}

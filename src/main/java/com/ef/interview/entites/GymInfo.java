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
@Document(collection = "gymInfo")
@JsonInclude(value = Include.NON_NULL)
public class GymInfo {

	@Id
	private String id;
	private String gymUserId;
    private String name;
    private String state;
    private String email;
 
}

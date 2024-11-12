package com.ef.interview.entites;

import java.time.LocalDateTime;

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
@Document(collection = "session")
@JsonInclude(value = Include.NON_NULL)
public class Session {


	    @Id
	    private String id;
	    private String trainerId;
	    private String customerId;
	    private LocalDateTime startTime;
	    private LocalDateTime endTime;
	    private boolean isBooked;

}

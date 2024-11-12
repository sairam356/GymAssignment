package com.ef.interview.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ef.interview.entites.Trainer;

public interface TrainerRepository extends MongoRepository<Trainer, String>  {

}

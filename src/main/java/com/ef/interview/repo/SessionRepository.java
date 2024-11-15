package com.ef.interview.repo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ef.interview.entites.Session;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessionRepository extends MongoRepository<Session, String> {

    List<Session> findByTrainerId(String trainerId);
    List<Session> findByCustomerId(String customerId);

    @Query("{ 'startTime' : { $gte: ?0, $lte: ?1 } }")
    List<Session> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}

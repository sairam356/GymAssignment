package com.ef.interview.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ef.interview.entites.Session;
import com.ef.interview.repo.SessionRepository;

@Service
public class SessionService {

	@Autowired
    private SessionRepository sessionRepository;

    public Session bookSession(String trainerId, String customerId, LocalDateTime startTime, LocalDateTime endTime) {
        // Check availability of trainer and customer
        if (!isAvailable(trainerId, startTime, endTime) || !isAvailable(customerId, startTime, endTime)) {
            throw new RuntimeException("Trainer or Customer is not available for the selected time slot.");
        }

        // Book session in UTC
        Session session = new Session();
        session.setTrainerId(trainerId);
        session.setCustomerId(customerId);
        session.setStartTime(startTime.atOffset(ZoneOffset.UTC).toLocalDateTime());
        session.setEndTime(endTime.atOffset(ZoneOffset.UTC).toLocalDateTime());
        session.setBooked(true);
        
        return sessionRepository.save(session);
    }

    public boolean isAvailable(String personId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Session> sessions = sessionRepository.findByStartTimeBetween(startTime, endTime);
        return sessions.stream().noneMatch(s -> s.getTrainerId().equals(personId) || s.getCustomerId().equals(personId));
    }

    public List<Session> searchSessionsByTrainer(String trainerId) {
        return sessionRepository.findByTrainerId(trainerId);
    }

    public List<Session> searchSessionsByCustomer(String customerId) {
        return sessionRepository.findByCustomerId(customerId);
    }

    public List<Session> searchSessionsByTimeInterval(LocalDateTime start, LocalDateTime end) {
        return sessionRepository.findByStartTimeBetween(start, end);
    }

    public String cancelSession(String sessionId, LocalDateTime cancelTime) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));
        
        if (session.getStartTime().isAfter(cancelTime.plusHours(24))) {
            // Free cancellation
            session.setBooked(false);
            sessionRepository.save(session);
            return "Session canceled free of charge";
        } else {
            // Charge for late cancellation
            session.setBooked(false);
            sessionRepository.save(session);
            return "Session canceled with charge";
        }
    }

    public Session rescheduleSession(String sessionId, LocalDateTime newStartTime, LocalDateTime newEndTime) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));

        if (!isAvailable(session.getTrainerId(), newStartTime, newEndTime) || 
            !isAvailable(session.getCustomerId(), newStartTime, newEndTime)) {
            throw new RuntimeException("Trainer or Customer is not available for the new time slot.");
        }

        session.setStartTime(newStartTime.atOffset(ZoneOffset.UTC).toLocalDateTime());
        session.setEndTime(newEndTime.atOffset(ZoneOffset.UTC).toLocalDateTime());
        return sessionRepository.save(session);
    }
}

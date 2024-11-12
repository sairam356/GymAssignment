
# Gym Management API

This API provides functionality for managing gym sessions, users, and trainers.

## Endpoints and `curl` Commands

### Get All Users
- **Description**: Retrieves all users.
- **`curl` Command**:
  ```bash
  curl -X GET http://localhost:8080/api/users
  ```

### Get User by ID
- **Description**: Retrieves a user by their ID.
- **`curl` Command**:
  ```bash
  curl -X GET http://localhost:8080/api/users/{userId}
  ```
  Replace `{userId}` with the actual user ID.

### Get All Trainers
- **Description**: Retrieves all trainers.
- **`curl` Command**:
  ```bash
  curl -X GET http://localhost:8080/api/trainers
  ```

### Get Trainer by ID
- **Description**: Retrieves a trainer by their ID.
- **`curl` Command**:
  ```bash
  curl -X GET http://localhost:8080/api/trainers/{trainerId}
  ```
  Replace `{trainerId}` with the actual trainer ID.

### Book a Session
- **Description**: Books a session with a trainer and customer.
- **`curl` Command**:
  ```bash
  curl -X POST "http://localhost:8080/api/book" \
       -d "trainerId=123" \
       -d "customerId=456" \
       -d "startTime=2024-11-12T09:00:00" \
       -d "endTime=2024-11-12T10:00:00"
  ```
  Replace `trainerId`, `customerId`, `startTime`, and `endTime` with actual values.

### Search Sessions by Trainer
- **Description**: Retrieves sessions by trainer ID.
- **`curl` Command**:
  ```bash
  curl -X GET "http://localhost:8080/api/search/by-trainer?trainerId={trainerId}"
  ```
  Replace `{trainerId}` with the actual trainer ID.

### Search Sessions by Customer
- **Description**: Retrieves sessions by customer ID.
- **`curl` Command**:
  ```bash
  curl -X GET "http://localhost:8080/api/search/by-customer?customerId={customerId}"
  ```
  Replace `{customerId}` with the actual customer ID.

### Search Sessions by Time Interval
- **Description**: Retrieves sessions within a specific time interval.
- **`curl` Command**:
  ```bash
  curl -X GET "http://localhost:8080/api/search/by-interval?start=2024-11-12T09:00:00&end=2024-11-12T10:00:00"
  ```
  Replace `start` and `end` with actual date-time values.

### Cancel a Session
- **Description**: Cancels a session with a specified ID.
- **`curl` Command**:
  ```bash
  curl -X PUT "http://localhost:8080/api/cancel/{sessionId}" -d "cancelTime=2024-11-11T08:00:00"
  ```
  Replace `{sessionId}` with the actual session ID and `cancelTime` with the cancellation time.

### Reschedule a Session
- **Description**: Reschedules a session with a new start and end time.
- **`curl` Command**:
  ```bash
  curl -X PUT "http://localhost:8080/api/reschedule/{sessionId}" \
       -d "newStartTime=2024-11-13T09:00:00" \
       -d "newEndTime=2024-11-13T10:00:00"
  ```
  Replace `{sessionId}`, `newStartTime`, and `newEndTime` with actual values.

---

### Notes

- Replace `{userId}`, `{trainerId}`, `{customerId}`, and `{sessionId}` with actual values from your database.
- Ensure date-time values are formatted as `YYYY-MM-DDTHH:MM:SS` (ISO-8601 format) for compatibility.

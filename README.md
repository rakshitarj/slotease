# Appointment Scheduling System

A full-stack web application that enables users to book appointments with service providers and allows providers to manage appointment requests.

## About the Project

The Appointment Scheduling System provides a platform for users and service providers to manage appointments efficiently.

Users can register, log in, browse service categories, select providers, book appointments, and track their appointment status. Providers can view incoming requests and accept or reject appointments.

## Features

- User registration and login
- Service provider registration and login
- Separate user and provider dashboards
- Service category selection
- Provider selection
- Appointment booking
- Provider approval and rejection of appointment requests
- Appointment status tracking
- MySQL database integration

## Technology Stack

**Backend**
- Java
- Spring Boot
- Hibernate / JPA
- REST APIs
- Maven

**Frontend**
- React.js
- JavaScript
- HTML
- CSS
- Axios

**Database**
- MySQL

## Project Structure

```text
appointment-scheduling-system/
├── appointment-backend/
│   └── src/
├── public/
├── src/
├── package.json
├── package-lock.json
├── .gitignore
└── README.md
```

## Prerequisites

- Java JDK 17 or later
- Maven
- Node.js and npm
- MySQL Server

## Database Configuration

Create the MySQL database:

```sql
CREATE DATABASE appointment_db;
```

Configure `appointment-backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/appointment_db
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}
```

Set your database password in PowerShell:

```powershell
$env:DB_PASSWORD="your_mysql_password"
```

Do not commit actual database credentials to GitHub.

## How to Run

### Backend

Open a terminal:

```powershell
cd appointment-backend
mvn spring-boot:run
```

Backend URL: `http://localhost:8080`

### Frontend

Open another terminal in the main project folder:

```powershell
npm install
npm start
```

Frontend URL: `http://localhost:3000`

Keep both terminals running while using the application.

## Application Workflow

1. User registers and logs in.
2. Provider registers and logs in.
3. User selects a service category.
4. User selects a provider.
5. User books an appointment.
6. Provider receives the appointment request.
7. Provider accepts or rejects the request.
8. User views the updated appointment status.

## Future Improvements

- Appointment cancellation
- Prevention of duplicate bookings
- Provider availability management
- Improved user interface
- Additional validation and error handling

## Author

**Rakshita Jadhav**

GitHub: [github.com/rakshitarj](https://github.com/rakshitarj)

# 🎟️ Event Booking System

Spring Boot backend for event management and ticket booking.

---

## ✨ Features

- User signup/login with **JWT authentication**  
- Role-based access control (**User**, **Admin**)  
- Admins can create, update, and delete events  
- Public users can view events without authentication  
- Authenticated users can:
  - Book tickets  
  - View their bookings  
  - Cancel bookings  
- System prevents overbooking and updates available seats

---

## 🛠️ Technologies Used

- Spring Boot  
- Spring Security (JWT)  
- Hibernate / JPA  
- H2 (in-memory database) or MySQL (optional)  
- Maven  
- JUnit & Mockito (for testing)  

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17 or higher  
- Maven 3.6+  
- (Optional) MySQL for persistent storage

---

### 📦 Installation & Running

### 1️⃣ Clone the repository

```bash
git clone https://github.com/hlavania05/Event-Booking-System.git
cd Event-Booking-System
```

---

### 2️⃣ Configure the database

✅ **For H2 (default):** no changes needed.

⚙ **For MySQL:** update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

### 3️⃣ Build and run the app

```bash
mvn clean install
mvn spring-boot:run
```

---

## 🔗 Useful URLs

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  

---

## 📡 API Endpoints

### 🔐 Authentication

- `POST /api/auth/register` — Register a new user  
- `POST /api/auth/login` — Login to receive JWT token  

### 👥 Users (Admin only)

- `GET /api/users` — Get all users  
- `GET /api/users/{id}` — Get user by ID  

### 🎫 Events

- `POST /api/events` — Create event (Admin only)  
- `GET /api/events` — Get all events  
- `GET /api/events/{id}` — Get event details  
- `PUT /api/events/{id}` — Update event (Admin only)  
- `DELETE /api/events/{id}` — Delete event (Admin only)  

### 📑 Bookings

- `POST /api/bookings/book?userId={userId}&eventId={eventId}` — Book event ticket  
- `GET /api/bookings/user/{userId}` — Get bookings for a user  
- `DELETE /api/bookings/cancel/{bookingId}?userId={userId}` — Cancel a booking  

---

## 🧪 Testing

Run all tests using:

```bash
mvn test
```

---

## 📄 License

This project is licensed under the **MIT License**.

---

## 🙌 Contributing

Pull requests are welcome!  
For major changes, please open an issue first to discuss what you would like to change.

⭐ **If you like this project, don’t forget to give it a star on GitHub!**

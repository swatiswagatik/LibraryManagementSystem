📚 Library Management System (Spring Boot)
📌 Project Description

This is a Library Management System developed using Spring Boot.
The project helps to manage books, users, and library operations like issuing and returning books.

🚀 Features

Add new books

View all books

Update book details

Delete books

User management

Issue and return books

Database integration using MySQL

🛠️ Technologies Used

Java

Spring Boot

Spring Data JPA

MySQL Database

Hibernate

Maven

Postman (for API testing)

📂 Project Structure
Library-Management-System
│
├── src/main/java
│   ├── controller
│   ├── service
│   ├── repository
│   └── model
│
├── src/main/resources
│   ├── application.properties
│
└── pom.xml

⚙️ How to Run the Project
1️⃣ Clone the Repository
git clone https://github.com/your-username/library-management-system.git

2️⃣ Open in Spring Tool Suite (STS)

File → Open Projects from File System

Select the project folder

3️⃣ Configure MySQL Database

Edit application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4️⃣ Run the Project

Right click project → Run As → Spring Boot App

📬 API Endpoints (Example)
Method	URL	Description
GET	/books	Get all books
POST	/books	Add new book
PUT	/books/{id}	Update book
DELETE	/books/{id}	Delete book
👩‍💻 Developer

Name: Swatiswagatika Pattnaik
Role: Software Developer Intern


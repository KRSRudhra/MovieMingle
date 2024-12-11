# 🎫 MovieMingle Ticketing System

MovieMingle is a comprehensive ticketing system that provides seamless management of ticketing processes. It comprises three main components:

1. **Frontend**: Built with Angular - [`event-system-frontend`](./event-system-frontend)
2. **Backend**: Built with Java Spring Boot - [`event-system-api`](./event-system-api)
3. **CLI**: Built with Core Java - [`TicketCLI`](./TicketCLI)

---

## 🛠 Prerequisites

Before running the project, ensure you have the following installed:

- **Java 19**: Required for the backend and CLI.
- **Maven**: For building and managing dependencies for the backend.
- **Gson Library**: Add via Maven repository (used for JSON parsing).
- **Angular CLI**: For running and managing the frontend.
- **Node.js**: Required for Angular applications.

---

## 🚀 How to Run the Project

Follow these steps to set up and run the Event-Hive system:

### 1️⃣ Clone the Project
Clone the repository to your local machine using the following command:
```bash
git clone https://github.com/KRSRudhra/MovieMingle.git
```

---

### 2️⃣ Run the Backend (`event-system-api`)
1. Navigate to the backend directory:
   ```bash
   cd event-system-api
   ```
2. Build and install dependencies using Maven:
   ```bash
   mvn clean install
   ```
3. Run the Spring Boot application:
   - From the terminal:
     ```bash
     mvn spring-boot:run
     ```
   - OR open the `event-system-api` project in your IDE (e.g., IntelliJ IDEA) and run the application directly.

---

### 3️⃣ Run the Frontend (`event-system-frontend`)
1. Navigate to the frontend directory:
   ```bash
   cd event-system-frontend
   ```
2. Serve the Angular application:
   - From the terminal:
     ```bash
     ng serve
     ```
   - OR open the `event-system-frontend` project in your IDE and use Angular CLI to serve the application.

---

### 4️⃣ Run the CLI (`TicketCLI`)
1. Open a terminal window and navigate to the CLI directory:
   ```bash
   cd TicketCLI
   ```
2. Open the project in your IDE (e.g., IntelliJ IDEA).
3. Locate the `Main` class and run it:
   - From your IDE, find the `Main` class in the project structure.
   - Run the class to start the CLI-based ticketing application.

---

## 📂 Project Structure
```
event-ticketing-system/
│
├── event-system-frontend/     # Frontend (Angular)
├── event-system-api/          # Backend (Spring Boot)
└── TicketCLI/                 # Command-Line Interface (Core Java)
```

---

## 🛠 Technologies Used

- **Frontend**:
   - Angular
   - TypeScript
   - HTML/CSS
   - Node.js

- **Backend**:
   - Java 19
   - Spring Boot
   - Maven

- **CLI**:
   - Core Java
   - Gson Library

---

## 📋 Notes

- Ensure all dependencies are properly installed before running any part of the project.
- Use the latest version of Angular CLI and Node.js for optimal performance.
- Check the `README.md` files in individual sub-projects for specific details if available.

---

## 🤝 Contributing

Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature-branch
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add your message here"
   ```
4. Push to the branch:
   ```bash
   git push origin feature-branch
   ```
5. Open a pull request.

---

Happy coding! 🚀

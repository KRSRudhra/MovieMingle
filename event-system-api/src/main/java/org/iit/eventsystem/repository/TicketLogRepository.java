package org.iit.eventsystem.repository;

import org.iit.eventsystem.domain.TicketLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Purpose:
        //1.Abstracts database operations for the TicketLog entity, enabling efficient create, read, update, and delete operations.
        //2.Serves as a bridge between the application and the database for TicketLog data.
//OOP Concepts Used:
        //1.Abstraction:
        //Simplifies database interactions by providing high-level methods like save, findById, and delete without requiring SQL or database details.
        //2.Encapsulation:
        //Encapsulates TicketLog-specific database operations, promoting separation of concerns and isolating data access logic.
        //3.Inheritance:
        //Inherits from JpaRepository, enabling pagination, sorting, and CRUD operations, reducing repetitive code.



@Repository
public interface TicketLogRepository extends JpaRepository<TicketLog, Long> {
}

package org.iit.eventsystem.repository;

import org.iit.eventsystem.domain.TicketPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Purpose:
        //1.Abstracts database operations for the TicketPool entity, enabling create, read, update, and delete operations.
        //2.Simplifies database interactions by leveraging inherited methods from JpaRepository.
//OOP Concepts Used:
        //1.Abstraction:
        //Provides high-level methods for interacting with TicketPool without exposing the complexities of underlying database operations.
        //2.Encapsulation:
        //Encapsulates all TicketPool-specific database operations, ensuring separation of concerns and isolating data access logic.
        //3.Inheritance:
        //Inherits CRUD functionality and advanced features like pagination and sorting from JpaRepository, reducing boilerplate code and improving maintainability.


@Repository
public interface TicketPoolRepository extends JpaRepository<TicketPool, Long> {

}


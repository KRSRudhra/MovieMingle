package org.iit.eventsystem.repository;

import org.iit.eventsystem.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

//Purpose:
       //1.Abstracts database operations for the Customer entity, reducing boilerplate code.
       //2.Provides methods to fetch customers by username/password, email, or ID for login and lookup.
//OOP Concepts Used:
       //1.Abstraction:
       //Hides database operation complexity and provides high-level methods like findCustomerByEmail without raw SQL.
       //2.Encapsulation:
       //Encapsulates all database operations for the Customer entity, promoting separation of concerns and isolating data access logic.
       //3.Inheritance:
       //Inherits methods from JpaRepository, such as save, findById, and delete, enabling automatic CRUD operations.


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findCustomerByUsernameAndPassword(String username, String password);

    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> findCustomerById(long customerId);

}

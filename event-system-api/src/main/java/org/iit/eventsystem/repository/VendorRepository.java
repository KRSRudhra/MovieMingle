package org.iit.eventsystem.repository;

import org.iit.eventsystem.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Purpose:
        //1.Abstracts database operations for the Vendor entity, supporting create, read, update, and delete operations.
        //2.Provides methods to fetch vendors based on credentials, email, or unique ID, supporting business logic like authentication and vendor lookup.
//OOP Concepts Used:
        //1.Abstraction:
        //Hides the complexities of database interactions and provides simple, high-level methods for accessing Vendor data.
        //For example, findVendorByEmail abstracts the query logic, allowing developers to easily fetch a vendor by email.
        //2.Encapsulation:
        //Encapsulates all database operations related to Vendor, promoting separation of concerns.
        //Isolates data access logic from other parts of the application.
        //3.Inheritance:
        //Inherits built-in CRUD functionality and features like pagination and sorting from JpaRepository, reducing the need for repetitive code.

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Long> {

    Optional<Vendor> findVendorByUsernameAndPassword(String username, String password);

    Optional<Vendor> findVendorByEmail(String email);

    Optional<Vendor> findVendorById(long id);

}

package org.iit.eventsystem.service;

import org.iit.eventsystem.domain.Customer;

// Purpose: Defines the contract for the CustomerService, which provides methods for creating customers and handling customer login.
// OOP Concepts Used:
// 1. Abstraction: Hides the implementation details of customer creation and login, providing high-level methods for interacting with customer data.
// 2. Interface: Defines a contract that concrete implementations must adhere to, promoting loose coupling and flexibility.
// 3. Encapsulation: Encapsulates the logic related to customer management within the service layer, ensuring separation of concerns.

public interface CustomerService {
    Customer createCustomer(String username, String email, String password, Long mobileNo, Boolean is_premium);

    void customerLogin(String username, String password);
}

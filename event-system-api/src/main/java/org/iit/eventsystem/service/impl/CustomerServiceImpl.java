package org.iit.eventsystem.service.impl;

import org.iit.eventsystem.domain.Customer;
import org.iit.eventsystem.exception.UserAlreadyExistsException;
import org.iit.eventsystem.exception.UserNotFoundException;
import org.iit.eventsystem.repository.CustomerRepository;
import org.iit.eventsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Purpose:
        //This class acts as an intermediary between the controller and repository layers, handling business logic related to customer management.

//OOP Concepts Used:
        //1.Abstraction:
        //The CustomerServiceImpl class abstracts customer-related business logic, hiding the complexities of interacting with repositories.
        //2.Encapsulation:
        //Encapsulates customer state (e.g., username, email, mobile number) and operations (e.g., creating a customer, handling login) within the Customer object.
        //3.Inheritance:
        //The CustomerServiceImpl class implements the CustomerService interface, inheriting its contract and providing specific method implementations.

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(String username, String email, String password, Long mobileNo, Boolean is_premium) {
        // Check if a customer with the same email exists
        if (customerRepository.findCustomerByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("Customer with email " + email + " already exists.");
        }
        // Check if isPremium is null and set default value
        if (is_premium == null) {
            is_premium = false; // Default value if null
        }

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setMobileNo(mobileNo);
        customer.setIs_premium(is_premium);
        return customerRepository.save(customer);
    }

    @Override
    public void customerLogin(String username, String password) {
        customerRepository.findCustomerByUsernameAndPassword(username, password)
                .orElseThrow(() -> new UserNotFoundException("Invalid username or password!"));
    }
}

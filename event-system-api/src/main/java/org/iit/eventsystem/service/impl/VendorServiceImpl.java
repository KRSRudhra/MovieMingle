package org.iit.eventsystem.service.impl;

import org.iit.eventsystem.domain.Config;
import org.iit.eventsystem.domain.TicketPool;
import org.iit.eventsystem.domain.Vendor;
import org.iit.eventsystem.exception.UserAlreadyExistsException;
import org.iit.eventsystem.exception.UserNotFoundException;
import org.iit.eventsystem.repository.ConfigRepository;
import org.iit.eventsystem.repository.VendorRepository;
import org.iit.eventsystem.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;


//Purpose:
        //This class serves as an intermediary between the controller and repository layers, handling business logic related to vendor management.

//OOP Concepts Used:
        //1.Abstraction:
        //The VendorServiceImpl class abstracts vendor management logic, hiding the details of repository interactions from the user.
        //2.Encapsulation:
        //Encapsulates the vendor's state (e.g., username, email, mobile number) and operations (e.g., creating a vendor, handling login) within the Vendor object.
        //3.Inheritance:
        //The VendorServiceImpl class implements the VendorService interface, inheriting its contract and providing specific method implementations.

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ConfigRepository configRepository;

    private boolean isAdminLoggedIn = false;
    private final ReentrantLock lock = new ReentrantLock();
    private long totalTickets;
    private TicketPool ticketPool;

    @Override
    public Vendor createVendor(String username, String email, String password, Long mobileNo, Boolean isAdmin) {
        // Check if a vendor with the same email exists
        if (vendorRepository.findVendorByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("Vendor with email " + email + " already exists.");
        }
        // Check if isAdmin is null and set default value
        if (isAdmin == null) {
            isAdmin = false; // Default value if null
        }

        Vendor vendor = new Vendor();
        vendor.setUsername(username);
        vendor.setEmail(email);
        vendor.setPassword(password);
        vendor.setMobileNo(mobileNo);
        vendor.setIsAdmin(isAdmin);
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor vendorLogin(String username, String password) {
        String adminPassword = "admin123";
        if (username.equals("admin") && password.equals(adminPassword)) {
            isAdminLoggedIn = true;
            return vendorRepository.findVendorByUsernameAndPassword(username, password)
                    .orElseThrow(() -> new UserNotFoundException("Invalid admin username or password!"));
        } else {
            return vendorRepository.findVendorByUsernameAndPassword(username, password)
                    .orElseThrow(() -> new UserNotFoundException("Invalid username or password!"));
        }
    }

//    @Override
//    public synchronized void setConfigurations(int totalTickets, int maxCapacity, int ticketReleaseRate, int customerRetrievalRate) {
//        lock.lock();
//        try {
////            if (!isAdminLoggedIn) {
////                throw new SecurityException("Only admins can set configurations.");
////            }
//
//            // Check if configuration already exists
//            if (configRepository.existsByTotalTicketsAndMaxTicketCapacityAndTicketReleaseRateAndCustomerRetrievalRate(
//                    (long) totalTickets, (long) maxCapacity, (long) ticketReleaseRate, (long) customerRetrievalRate)) {
//                throw new IllegalArgumentException("Configuration already exists. Please reset the configuration.");
//            }
//
//            // Update in-memory variables
//            this.totalTickets = totalTickets;
//            ticketPool = new TicketPool(maxCapacity, totalTickets);
//
//            // Persist configurations to the database
//            Config config = new Config();
//            config.setTotalTickets((long) totalTickets);
//            config.setMaxTicketCapacity((long) maxCapacity);
//            config.setTicketReleaseRate((long) ticketReleaseRate);
//            config.setCustomerRetrievalRate((long) customerRetrievalRate);
//            configRepository.save(config);
//
//            System.out.println("Parameters set successfully!");
//        } finally {
//            lock.unlock();
//        }
//    }

    @Override
    public synchronized void setConfigurations(int totalTickets, int maxCapacity, int ticketReleaseRate, int customerRetrievalRate) {
        lock.lock();
        try {
            System.out.println("Starting setConfigurations...");

            Long totalTicketsLong = (long) totalTickets;
            Long maxCapacityLong = (long) maxCapacity;
            Long ticketReleaseRateLong = (long) ticketReleaseRate;
            Long customerRetrievalRateLong = (long) customerRetrievalRate;

            System.out.println("Checking if configuration exists...");
            boolean exists = configRepository.existsByTotalTicketsAndMaxTicketCapacityAndTicketReleaseRateAndCustomerRetrievalRate(
                    totalTicketsLong, maxCapacityLong, ticketReleaseRateLong, customerRetrievalRateLong);
            System.out.println("Configuration exists: " + exists);

            if (exists) {
                System.out.println("Configuration found, throwing exception.");
                throw new IllegalArgumentException("Configuration already exists. Please reset the configuration.");
            }

            // Proceed with saving the configuration
            System.out.println("Saving configuration...");
            Config config = new Config();
            config.setTotalTickets(totalTicketsLong);
            config.setMaxTicketCapacity(maxCapacityLong);
            config.setTicketReleaseRate(ticketReleaseRateLong);
            config.setCustomerRetrievalRate(customerRetrievalRateLong);
            configRepository.save(config);

            System.out.println("Parameters set successfully!");
        } finally {
            lock.unlock();
        }
    }



}

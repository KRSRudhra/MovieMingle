package org.iit.eventsystem.service;

import org.iit.eventsystem.domain.Vendor;

// Purpose: Defines the contract for the VendorService, which provides methods for creating vendors and handling vendor login.
// OOP Concepts Used:
// 1. Abstraction: Hides the implementation details of vendor creation and login, providing high-level methods for interacting with vendor data.
// 2. Interface: Defines a contract that concrete implementations must adhere to, promoting loose coupling and flexibility.
// 3. Encapsulation: Encapsulates the logic related to vendor management within the service layer, ensuring separation of concerns.

public interface VendorService {

    Vendor createVendor(String username, String email, String password, Long mobileNo, Boolean isAdmin);

    Vendor vendorLogin(String username, String password);

    void setConfigurations(int totalTickets, int maxCapacity, int ticketReleaseRate, int customerRetrievalRate);
}

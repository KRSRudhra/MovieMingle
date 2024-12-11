package org.iit.eventsystem.service;

import org.iit.eventsystem.domain.Config;
import org.iit.eventsystem.domain.TicketPool;
import org.iit.eventsystem.dto.ConfigDto;

// Purpose:
// - Defines the contract for the ConfigService, which provides methods for managing configuration settings and ticket pools.
// - The service interface specifies operations like resetting configuration values, adding tickets to the pool, and purchasing tickets.
// OOP Concepts Used:
// 1. Abstraction: Hides the implementation details of interacting with the database and ticket pool, providing high-level methods.
// 2. Encapsulation: Encapsulates the logic related to configuration management and ticket pool operations within the service layer.
// 3. Interface: Defines a contract that concrete implementations must adhere to, promoting loose coupling and flexibility.

public interface ConfigService {

    Config resetConfigValue(ConfigDto configDto);

    Config getCurrentConfig();

    void addTicketsToPool(long ticketsToAdd, long vendorId);

    void purchaseTicketsFromPool(long ticketsToPurchase, long customerId);

    TicketPool getTicketPool();
}

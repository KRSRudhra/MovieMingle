package org.iit.eventsystem.service.impl;


import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.iit.eventsystem.domain.*;
import org.iit.eventsystem.dto.ConfigDto;
import org.iit.eventsystem.repository.*;
import org.iit.eventsystem.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

//Purpose:
        //1.The service acts as a bridge between the controller (API layer) and the data access layer (repositories), applying business logic before interacting with the database.
        //2.Manages ticket pool configuration (total tickets, max capacity, etc.), adds tickets to the pool, and handles customer ticket purchases.
        //3.Handles the creation and resetting of ticket configurations based on the ConfigDto object.
        //4.Logs each transaction (adding or purchasing tickets) for auditing and tracking purposes.

//OOP Concepts Used:
        //1.Abstraction:
        //The ConfigServiceImpl class abstracts the business logic related to ticket management, hiding the details of interacting with repositories.
        //Methods like addTicketsToPool and purchaseTicketsFromPool abstract database and ticket pool operations.
        //2.Encapsulation:
        //Encapsulates the ticket pool's state and operations within the TicketPool object.
        //Database access is encapsulated within repository interfaces, improving modularity and maintainability.
        //3.Inheritance:
        //The ConfigServiceImpl class implements the ConfigService interface, inheriting its contract and providing specific implementations.
        //Enables polymorphism, allowing interchangeable service implementations if needed.
        //4.Polymorphism:
        //By using the ConfigService interface, polymorphism allows different implementations (e.g., ConfigServiceImpl) to be injected and used without changing the calling code.

@Service
public class ConfigServiceImpl implements ConfigService {

    private static final Logger log = Logger.getLogger(ConfigServiceImpl.class.getName());

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private TicketPoolRepository ticketPoolRepository;

    @Autowired
    private TicketLogRepository ticketLogRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private TicketPool ticketPool;

    /*
    @PostConstruct: This annotation ensures that the ticketPool is initialized after the ConfigServiceImpl object is created.
    */
    @PostConstruct
    public void init() {
        // Check if the ticket pool exists in the database
        ticketPool = ticketPoolRepository.findById(1L).orElse(null);

        // If the ticket pool does not exist, create a new one with default values
        if (ticketPool == null) {
            ticketPool = new TicketPool();
            ticketPool.setAvailableTickets(0); // Default value
            ticketPool.setReleasedTickets(0);  // Default value
            ticketPoolRepository.save(ticketPool);  // Save the new pool to the database
            log.info("New ticket pool created with ID 1.");
        } else {
            log.info("Existing ticket pool loaded with ID 1.");
        }
    }

    @Override
    public Config getCurrentConfig() {
        log.info("Fetching current configuration...");
        return configRepository.getConfigById(1L)
                .orElseThrow(() -> new RuntimeException("Configuration not found."));
    }

    @Override
    public Config resetConfigValue(ConfigDto configDto) {
        log.info("Resetting configuration values...");
        Config config = configRepository.getConfigById(1L).orElse(new Config());
        if (config.getId() == null) {
            config.setId(1L); // Ensure ID is set for new records
            log.info("New configuration created with ID: 1");
        }

        config.setTotalTickets((long) configDto.getTotalTickets());
        config.setMaxTicketCapacity((long) configDto.getMaxTicketCapacity());
        config.setTicketReleaseRate((long) configDto.getTicketReleaseRate());
        config.setCustomerRetrievalRate((long) configDto.getCustomerRetrievalRate());

        Config savedConfig = configRepository.save(config);
        log.info(String.format("Configuration saved with ID: %d, Values: %s", savedConfig.getId(), savedConfig));
        return savedConfig;

    }

    @Transactional
    @Override
    public synchronized void addTicketsToPool(long ticketsToAdd, long vendorId) {
        Optional<Vendor> vendor = vendorRepository.findVendorById(vendorId);
        if (vendor.isEmpty()) {
            throw new IllegalArgumentException("Invalid vendor ID: " + vendorId);
        }
        Config config = getCurrentConfig();
        try {
            ticketPool.addTickets(ticketsToAdd, config.getMaxTicketCapacity(), config.getTotalTickets());
            ticketPoolRepository.save(ticketPool);
            logTransaction(vendorId, null, ticketsToAdd, 0);
            log.info(ticketsToAdd + " tickets added successfully. Current available: " +
                    ticketPool.getAvailableTickets());
        } catch (IllegalStateException e) {
            log.warning("Failed to add tickets: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public synchronized void purchaseTicketsFromPool(long ticketsToPurchase, long customerId) {
        Optional<Customer> customer = customerRepository.findCustomerById(customerId);
        if (customer.isEmpty()) {
            throw new IllegalArgumentException("Invalid customer ID: " + customerId);
        }
        getCurrentConfig();
        try {
            ticketPool.purchaseTickets(ticketsToPurchase);
            ticketPoolRepository.save(ticketPool);
            logTransaction(null, customerId, 0, ticketsToPurchase);
            log.info(ticketsToPurchase + " tickets purchased successfully. Current available: " +
                    ticketPool.getAvailableTickets());
        } catch (IllegalStateException e) {
            log.warning("Failed to purchase tickets: " + e.getMessage());
            throw e;
        }
    }

    private void logTransaction(Long vendorId, Long customerId, long ticketsAdded, long ticketsPurchased) {
        TicketLog log = new TicketLog();
        log.setVendorId(vendorId);
        log.setCustomerId(customerId);
        log.setTicketsAdded(ticketsAdded);
        log.setTicketsPurchased(ticketsPurchased);
        log.setTimestamp(new Date());

        // Save the log to the database
        ticketLogRepository.save(log);
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }

}

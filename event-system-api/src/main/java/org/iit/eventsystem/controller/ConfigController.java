package org.iit.eventsystem.controller;

import org.iit.eventsystem.domain.Config;
import org.iit.eventsystem.dto.ConfigDto;
import org.iit.eventsystem.service.ConfigService;
import org.iit.eventsystem.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//This class manages HTTP requests for configuration settings using OOP principles:
// 1. Abstraction: Simplifies setting and retrieving configurations via high-level methods.
// 2. Encapsulation: Contains configuration logic within the controller, ensuring separation of concerns.

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @Autowired
    private VendorService vendorService;

    @PostMapping("/init-config")
    public ResponseEntity<String> setConfigurations(@RequestBody ConfigDto configDto) {
        try {
            // Validate ConfigDto values
            validateConfigDto(configDto);

            // Extract fields and pass to VendorService
            int totalTickets = configDto.getTotalTickets();
            int maxCapacity = configDto.getMaxTicketCapacity();
            int ticketReleaseRate = configDto.getTicketReleaseRate();
            int customerRetrievalRate = configDto.getCustomerRetrievalRate();

            vendorService.setConfigurations(totalTickets, maxCapacity, ticketReleaseRate, customerRetrievalRate);
            return ResponseEntity.ok("Parameters set successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }


    @GetMapping("/get-config")
    public ResponseEntity<Config> getConfig() {
        Config config = configService.getCurrentConfig();
        if (config == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(config);
    }

    @PostMapping("/set-config")
    public ResponseEntity<Config> resetConfig(@RequestBody ConfigDto configDto) {
        // Validate ConfigDto values manually
        validateConfigDto(configDto);

        // Proceed with the rest of the logic
        Config config = configService.resetConfigValue(configDto);
        return ResponseEntity.ok(config);
    }

    private void validateConfigDto(ConfigDto configDto) {
        // Validate individual fields
        if (configDto.getTotalTickets() <= 0) {
            throw new IllegalArgumentException("The total number of tickets must exceed 0.");
        }
        if (configDto.getMaxTicketCapacity() <= 0) {
            throw new IllegalArgumentException("The maximum capacity must be more than 0.");
        }
        if (configDto.getTicketReleaseRate() <= 0) {
            throw new IllegalArgumentException("The ticket release rate must exceed 0.");
        }
        if (configDto.getCustomerRetrievalRate() <= 0) {
            throw new IllegalArgumentException("The customer retrieval rate must be more than 0.");
        }

        // Additional validation: max capacity cannot be greater than total tickets.
        if (configDto.getMaxTicketCapacity() > configDto.getTotalTickets()) {
            throw new IllegalArgumentException("Max capacity cannot exceed total tickets.");
        }

        // Additional validation: the ticket release rate cannot exceed the maximum capacity.
        if (configDto.getTicketReleaseRate() > configDto.getMaxTicketCapacity()) {
            throw new IllegalArgumentException("Ticket release rate cannot exceed max capacity.");
        }

        // Additional validation: the ticket release rate cannot be greater than the total tickets.
        if (configDto.getTicketReleaseRate() > configDto.getTotalTickets()) {
            throw new IllegalArgumentException("Ticket release rate cannot exceed total tickets.");
        }
    }

}

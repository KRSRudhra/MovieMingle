package org.iit.eventsystem.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name ="ticket_pool")
@Data
public class TicketPool implements Serializable {

    @Serial
    private static final long serialVersionUID = 5L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "available_tickets", nullable = false)
    private long availableTickets;

    @Column(name = "released_tickets", nullable = false)
    private long releasedTickets;

    // This method checks if a specified number of tickets can be added without exceeding capacity or total tickets
    // This class demonstrates encapsulation and abstraction by providing synchronized methods
    public synchronized boolean canAddTickets(long ticketsToAdd, long maxCapacity, long totalTickets) {
        // Encapsulation: Logic for ticket validation is enclosed within this method
        // Abstraction: External users don't need to know the details of this check,
        // they just call the method to verify if tickets can be added.
        if (releasedTickets + ticketsToAdd > totalTickets) {
            return false; // Exceeds total tickets
        }
        if (availableTickets + ticketsToAdd > maxCapacity) {
            return false; // Exceeds pool capacity
        }
        return true;
    }

    // This method safely adds tickets to the pool, ensuring capacity and total limits are respected
    public synchronized void addTickets(long ticketsToAdd, long maxCapacity, long totalTickets) {
        // Encapsulation: Manages ticket addition while protecting internal data integrity
        // Abstraction: Hides validation logic and exposes a simple API for adding tickets

        // Verify if tickets can be added using the 'canAddTickets' method
        if (!canAddTickets(ticketsToAdd, maxCapacity, totalTickets)) {
            throw new IllegalStateException("Cannot add tickets; pool capacity or total ticket limit exceeded.");
        }
        // Safely add tickets to the pool
        availableTickets += ticketsToAdd;
        releasedTickets += ticketsToAdd;
    }

    // This method allows tickets to be purchased while maintaining the ticket pool's integrity
    public synchronized void purchaseTickets(long ticketsToPurchase) {
        // Encapsulation: Logic for purchasing tickets is enclosed, preventing direct field manipulation
        // Abstraction: Simplifies ticket purchase by providing a clear interface

        if (ticketsToPurchase > availableTickets) {
            throw new IllegalStateException("Insufficient tickets available for purchase.");
        }
        // Safely decrease the available tickets count
        availableTickets -= ticketsToPurchase;
    }

    public long getAvailableTickets() {
        return availableTickets;
    }

    public long getReleasedTickets() {
        return releasedTickets;
    }

    public void setAvailableTickets(long availableTickets) {
        this.availableTickets = availableTickets;
    }

    public void setReleasedTickets(long releasedTickets) {
        this.releasedTickets = releasedTickets;
    }
}

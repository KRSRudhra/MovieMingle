package org.iit.eventsystem.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity // This annotation indicates that the class is a JPA entity
@Table(name ="config") //table name db and table connects here
@Data // Generates getter, setter, equals, hashCode, and toString methods automatically
public class Config implements Serializable { // Implements Serializable to enable object serialization and deserialization
    @Serial
    private static final long serialVersionUID = 5L; // Unique ID for the Serializable class to verify during deserialization

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_tickets", nullable = false)
    private Long totalTickets;

    @Column(name = "max_ticket_capacity", nullable = false)
    private Long maxTicketCapacity;

    @Column(name = "ticket_release_rate", nullable = false)
    private Long ticketReleaseRate;

    @Column(name = "customer_retrieval_rate", nullable = false)
    private Long customerRetrievalRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(Long totalTickets) {
        this.totalTickets = totalTickets;
    }

    public Long getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(Long maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public Long getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(Long ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public Long getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(Long customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }
}


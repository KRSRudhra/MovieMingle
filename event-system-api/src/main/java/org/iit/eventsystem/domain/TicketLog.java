package org.iit.eventsystem.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.util.Date;

@Entity
@Table(name = "ticket_log")
@Data
public class TicketLog {

    @Serial
    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "customer_id", nullable = true)
    private Long customerId;

    @Column(name = "vendor_id", nullable = true)
    private Long vendorId;

    @Column(name = "tickets_added", nullable = false)
    private Long ticketsAdded;

    @Column(name = "tickets_purchased", nullable = false)
    private Long ticketsPurchased;
}

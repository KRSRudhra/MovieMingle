package org.iit.eventsystem.dto;

import lombok.Data;

@Data
public class TicketRequestDto {
    private String type;
    private long count;
    private long vendorId;
    private long customerId;
}

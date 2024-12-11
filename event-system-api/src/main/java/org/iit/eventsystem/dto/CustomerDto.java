package org.iit.eventsystem.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private String username;
    private String email;
    private String password;
    private Long mobileNo;
    private Boolean isPremium;


    public Boolean getIs_premium() {
        return isPremium;
    }

    //1.Encapsulation: Protects the internal state by using private fields and controlling access through getters and setters.
    //2.Abstraction: Offers a unified object for transferring configuration data, hiding the specifics of individual fields.
}

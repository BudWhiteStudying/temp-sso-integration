package com.budwhite.studying.externalssodemo.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInformation {
    private final String userFullName;
    private String userRegistrationNumber;
    private String userRole;
    
    public UserInformation(String userFullName) {
        this.userFullName = userFullName;
    }
}

package com.company.company.service.dto;

import com.company.company.util.NotNullByDefault;

@NotNullByDefault
public interface EmailService {
    void sendMessageToAdmins(String username) throws Exception;
}

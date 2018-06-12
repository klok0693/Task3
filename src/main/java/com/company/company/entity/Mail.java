package com.company.company.entity;

import com.company.company.NotNullByDefault;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NotNullByDefault

@Data
@NoArgsConstructor
public class Mail {

    private String from = "admmbtask@gmail.com";
    private String to;
    private String subject;
    private String content;

    Map<String, Object> model;
}

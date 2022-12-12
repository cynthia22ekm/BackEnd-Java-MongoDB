package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="AutomationStatus")
public class AutomationStatus {
    @Id
    private String id;
    private String automationName;
    private String automationStatus;
    private Date startTime;
    private Date endTime;
}

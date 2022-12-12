package com.example.demo.controller;

import com.example.demo.model.AutomationStatus;
import com.example.demo.service.AutomationStatusService;
import com.example.demo.service.GetAutomationResponse;
import com.example.demo.service.PostAutomationStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//https://mkyong.com/spring-boot/spring-rest-spring-security-example/
//https://howtodoinjava.com/spring-boot2/security-rest-basic-auth-example/
import java.util.Optional;

@RestController
@RequestMapping("/AutomationStatus")
public class AutomationStatusController {

    @Autowired
    private AutomationStatusService service;

    @RequestMapping(value="/GetAllStatus", method= RequestMethod.GET)
    public GetAutomationResponse getStatus()
    {
        return service.getAllAutomationStatus();
    }

    @RequestMapping(value="/GetStatusByName/{name}", method= RequestMethod.GET)
    public AutomationStatus getStatusByName(@PathVariable String name)
    {
        return service.getAutomationStatusByName(name);
    }

    @RequestMapping(value="/CreateStatus", method= RequestMethod.POST)
    public PostAutomationStatusResponse postAutomationStatus(@RequestBody AutomationStatus automationStatus)
    {
       return service.postAutomationResponse(automationStatus);
    }

}

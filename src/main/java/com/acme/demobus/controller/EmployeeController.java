package com.acme.demobus.controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.*;

import java.util.UUID;

import com.acme.demobus.model.*;

import com.acme.demobus.integration.amqp.ProducerIntegration;

@RestController
@RequestMapping(value ="api/acme", produces ="application/json")
public class EmployeeController {
    
    private ProducerIntegration<Employee> integration;

    public EmployeeController(ProducerIntegration<Employee> integration){
        this.integration = integration;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saludo(@RequestParam String nombre){
        Employee emp = new Employee();
        emp.setName(nombre);
        emp.setId(UUID.randomUUID().toString());
        integration.sendMessage(emp);
        return  new ResponseEntity<String>(
            "Saludo " + nombre, HttpStatus.OK);
    }
    
}

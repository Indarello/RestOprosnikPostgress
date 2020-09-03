package com.oprosi.restservice.controller;


import com.oprosi.restservice.Repository.OprosService;
import com.oprosi.restservice.entities.Opros;
import com.oprosi.restservice.entities.Vopros;
import org.springframework.beans.factory.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import java.util.List;

@RestController
public class OprosRestController
{
    @Autowired
    private OprosService oprosService;


    @PutMapping(value = "/addOpros")
    public boolean addOpros
    (
        @RequestParam("title") String title,
        @RequestParam("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateFrom,
        @RequestParam("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTo,
        @RequestParam("active") boolean active
    )
    {
        oprosService.saveOpros(new Opros(title, dateFrom, dateTo, active));

        return true;
    }

    @PostMapping(value = "/listOpros")
    public List<Opros> listOprosi(@RequestParam("orderby") int orderby)
    {
        return oprosService.getAllOprosOrderedby(orderby);
    }

    @PostMapping(value = "/updateOpros")
    public boolean updateOpros
    (
        @RequestParam("id") long id,
        @RequestParam("title") String title,
        @RequestParam("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateFrom,
        @RequestParam("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTo,
        @RequestParam("active") boolean active
    )
    {
        Opros oprostoopdate = new Opros(title, dateFrom, dateTo, active);
        oprostoopdate.setId(id);

        oprosService.updateOpros(oprostoopdate);

        return true;
    }

    @PostMapping(value = "/deleteOpros")
    public boolean deleteOpros(@RequestParam("id") long id)
    {
        oprosService.deleteOpros(id);
        return true;
    }


    @PutMapping(value = "/addVopros")
    public boolean addVopros
    (
             @RequestParam("opros_id") long opros_id,
             @RequestParam("poradok") long poradok,
             @RequestParam("description") String description
    )
    {
        oprosService.saveVopros(new Vopros(new Opros(opros_id), poradok, description));

        return true;
    }

    @PostMapping(value = "/listVopros")
    public List<Vopros> listVoprosi(@RequestParam("opros_id") long opros_id)
    {
        return oprosService.getAllVopros(opros_id);
    }

    @PostMapping(value = "/deleteVopros")
    public boolean deleteVopros(@RequestParam("id") long id)
    {
        oprosService.deleteVopros(id);
        return true;
    }
}
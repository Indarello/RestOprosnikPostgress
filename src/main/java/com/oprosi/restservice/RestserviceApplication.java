package com.oprosi.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class RestserviceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RestserviceApplication.class, args);
        System.out.println(LocalDateTime.now());
    }

}

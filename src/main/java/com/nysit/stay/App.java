package com.nysit.stay;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.ServletComponentScan;

import org.springframework.context.annotation.ComponentScan;


/**
 * Hello world!
 *
 */

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@ServletComponentScan
public class App{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);   
    }
   
}

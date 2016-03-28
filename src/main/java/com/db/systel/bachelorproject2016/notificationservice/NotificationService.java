package com.db.systel.bachelorproject2016.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.db.systel.bachelorproject2016.notificationservice.api.NotificationController;

@SpringBootApplication
public class NotificationService {
	
	public static void main(String args[]){
		SpringApplication.run(NotificationController.class, args);
	}
	
	

}

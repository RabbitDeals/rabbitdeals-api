package com.rabbit_deals.RabbitDeals;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class RabbitDealsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitDealsApplication.class, args);
	}
}

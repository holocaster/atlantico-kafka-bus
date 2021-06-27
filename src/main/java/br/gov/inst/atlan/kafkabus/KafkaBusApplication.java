package br.gov.inst.atlan.kafkabus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableKafka
@EnableDiscoveryClient
@EnableEurekaClient
public class KafkaBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaBusApplication.class, args);
	}



}

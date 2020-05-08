package com.producer;

import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.PulsarClientException.UnsupportedAuthenticationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.producer.entity")
public class PulserProducerApplication {

	public static void main(String[] args) throws UnsupportedAuthenticationException, PulsarClientException {
		SpringApplication.run(PulserProducerApplication.class, args);

	}

}

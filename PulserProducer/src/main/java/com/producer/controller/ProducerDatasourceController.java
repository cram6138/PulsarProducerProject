package com.producer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producer.service.ProducerDatasourceService;

@RestController
public class ProducerDatasourceController {
	
	@Autowired
	private ProducerDatasourceService producerDatasourceService;
	
	@GetMapping("/data")
	public List<Map<String, String>> getProducerData() {
		return producerDatasourceService.getData();
	}

}

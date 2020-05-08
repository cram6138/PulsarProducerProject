package com.producer.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.pulsar.client.api.CompressionType;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.PulsarClientException.UnsupportedAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producer.entity.Config;
import com.producer.entity.ProducerDatasource;
import com.producer.entity.RequestStatus;
import com.producer.enums.RequestEnum;
import com.producer.repository.ConfigRepository;
import com.producer.repository.RequestStatusRepository;

@Service
public class ProducerDatasourceService {

	/*
	 * @Autowired private ProducerDatasourceRepository producerDatasourceRepo;
	 */

	@Autowired
	private ConfigRepository configRepo;

	@Autowired
	private RequestStatusRepository requestStatusRepo;

	public List<Map<String, String>> getData() {
		List<RequestStatus> requestStatusList = requestStatusRepo.findRequestStatusWithNew();
		List<Map<String, String>> finalData = new ArrayList<Map<String, String>>();
		if (!requestStatusList.isEmpty()) {
			for (RequestStatus request : requestStatusList) {
				Map<String, String> producerMap = request.getProducerData().stream().collect(
						Collectors.toMap(ProducerDatasource::getPropertyName, ProducerDatasource::getPropertyValue));
				finalData.add(producerMap);
				request.setStatus(RequestEnum.PUBLISHED.getValue());
			}
			requestStatusRepo.saveAll(requestStatusList);
		}

		// service url : "pulsar+ssl://vmb-aws-us-west-2-nonprod.verizon.com:6651/"
		// ==================== producer logic ================================

		// BasicConfigurator.configure(); 
		// Map which will hold the application specific key and cert file
		Config config = configRepo.getOne(1);
		Map<String, String> authParams = new HashMap<>();
		authParams.put("tlsCertFile", new File(config.getTlsCertFile()).getAbsolutePath());
		authParams.put("tlsKeyFile", new File(config.getTlsKeyFile()).getAbsolutePath());
		PulsarClient client;
		try { 
			// specify the Servicel URL
			client = PulsarClient.builder().serviceUrl(config.getServiceUrl()).enableTls(true)
					.allowTlsInsecureConnection(false)
					.tlsTrustCertsFilePath(new File(config.getTlsTrustCertsFilePath()).getAbsolutePath())
					.authentication("org.apache.pulsar.client.impl.auth.AuthenticationTls", authParams).build();
			Producer<byte[]> producer = client.newProducer()
					.topic("non-persistent://fuav/vran2-milestone/FUZE-VRAN2-Milestone-1ms")
					.compressionType(CompressionType.LZ4).create(); // The producer send can be keep in a loop to send
																	// multiple messages
			producer.send(finalData.toString().getBytes());
			producer.close();
			client.close();
		} catch (UnsupportedAuthenticationException e) {
			e.printStackTrace();
		} catch (PulsarClientException e) {
			e.printStackTrace();
		}

		// ========================================================================================================================
		return finalData;
	}
}

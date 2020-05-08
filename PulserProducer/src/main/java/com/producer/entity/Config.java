package com.producer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONFIG")
public class Config {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "SERVICE_URL")
	private String serviceUrl;
	
	@Column(name = "TOPIC")
	private String topic;
	
	@Column(name = "TLS_CERT_FILE")
	private String tlsCertFile;
	
	@Column(name = "TLS_KEY_FILE")
	private String tlsKeyFile;
	
	@Column(name = "TLS_TRUST_CERT_FILE_PATH")
	private String tlsTrustCertsFilePath;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTlsCertFile() {
		return tlsCertFile;
	}

	public void setTlsCertFile(String tlsCertFile) {
		this.tlsCertFile = tlsCertFile;
	}

	public String getTlsKeyFile() {
		return tlsKeyFile;
	}

	public void setTlsKeyFile(String tlsKeyFile) {
		this.tlsKeyFile = tlsKeyFile;
	}

	public String getTlsTrustCertsFilePath() {
		return tlsTrustCertsFilePath;
	}

	public void setTlsTrustCertsFilePath(String tlsTrustCertsFilePath) {
		this.tlsTrustCertsFilePath = tlsTrustCertsFilePath;
	}
	
}

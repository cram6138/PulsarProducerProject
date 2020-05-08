package com.producer.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "REQUEST_STATUS")
public class RequestStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "REQUEST_DATE")
	private Date requestDate;
	
	@OneToMany(mappedBy = "requestStatus")
	private List<ProducerDatasource> producerData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public List<ProducerDatasource> getProducerData() {
		return producerData;
	}

	public void setProducerData(List<ProducerDatasource> producerData) {
		this.producerData = producerData;
	}
	
	
}

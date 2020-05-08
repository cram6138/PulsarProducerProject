package com.producer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.producer.entity.RequestStatus;

public interface RequestStatusRepository extends JpaRepository<RequestStatus, Integer> {

	@Query("SELECT rs FROM RequestStatus as rs WHERE rs.status = 'NEW'")
	List<RequestStatus> findRequestStatusWithNew();

}

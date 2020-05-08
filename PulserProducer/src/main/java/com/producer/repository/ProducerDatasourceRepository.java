package com.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.producer.entity.ProducerDatasource;

public interface ProducerDatasourceRepository extends JpaRepository<ProducerDatasource, Integer>{

}

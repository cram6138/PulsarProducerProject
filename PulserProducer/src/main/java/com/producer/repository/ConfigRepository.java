package com.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.producer.entity.Config;

public interface ConfigRepository extends JpaRepository<Config, Integer> {

}

package com.example.inventory.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long>{
	 List<Stock> findByName(String name);
}

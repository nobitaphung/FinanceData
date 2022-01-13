package com.pmkha.stockdata.repository;

import com.pmkha.stockdata.model.StockUS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockUS, String> {
}

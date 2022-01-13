package com.pmkha.stockdata.service;

import com.pmkha.stockdata.model.StockUS;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICustomStockService {

    List<StockUS> findAllStock();

    List<StockUS> findRedStock();

    List<StockUS> findGreenStock();

    List<StockUS> find10MostTradedVolStock();

    List<StockUS> find10MostGreenStock();

    List<StockUS> find10MostRedStock();
}

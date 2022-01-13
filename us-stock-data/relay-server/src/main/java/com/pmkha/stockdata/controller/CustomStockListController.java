package com.pmkha.stockdata.controller;

import com.pmkha.stockdata.model.StockUS;
import com.pmkha.stockdata.service.ICustomStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/custom")
public class CustomStockListController {

    @Autowired
    private ICustomStockService customStockService;

    @GetMapping("/all")
    public List<StockUS> findAllStock() {
        return customStockService.findAllStock();
    }

    @GetMapping("/red")
    public List<StockUS> findBlueStock() {
        return customStockService.findRedStock();
    }

    @GetMapping("/green")
    public List<StockUS> findGreenStock() {
        return customStockService.findGreenStock();
    }

    @GetMapping("/most-trading")
    public List<StockUS> findMostTradedStock() {
        return customStockService.find10MostTradedVolStock();
    }

    @GetMapping("/most-green")
    public List<StockUS> findMostGreenStock() {
        return customStockService.find10MostGreenStock();
    }

    @GetMapping("/most-red")
    public List<StockUS> findMostRedStock() {
        return customStockService.find10MostRedStock();
    }
}

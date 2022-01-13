package com.pmkha.stockdata.service;

import com.pmkha.stockdata.model.StockUS;
import com.pmkha.stockdata.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CustomStockService implements ICustomStockService {

    private Logger logger = LoggerFactory.getLogger(CustomStockService.class);

    @Autowired
    StockRepository stockRepository;

    private static <K, V extends Comparable<? super V>> Map<K, V> descendingSortByValue(Map<K, V> map) {
            List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
           Comparator c = Map.Entry.comparingByValue().reversed();
           list.sort(c);

           Map<K, V> result = new LinkedHashMap<>();
            for (Map.Entry<K, V> entry : list) {
                result.put(entry.getKey(), entry.getValue());
            }

            return result;
        }

    private static <K, V extends Comparable<? super V>> Map<K, V> ascendingSortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    @Override
    public List<StockUS> findAllStock() {
        return stockRepository.findAll();
    }

    @Override
    public List<StockUS> findRedStock() {
        List<StockUS> redStockList = new ArrayList<>();
        for(StockUS eachStockUS : stockRepository.findAll()) {
            if(eachStockUS.getClosePrice() < eachStockUS.getOpenPrice()) {
                redStockList.add(eachStockUS);
            }
        }
        return redStockList;
    }

    @Override
    public List<StockUS> findGreenStock() {
        List<StockUS> greenStockList = new ArrayList<>();
        for(StockUS eachStockUS : stockRepository.findAll()) {
            if(eachStockUS.getClosePrice() > eachStockUS.getOpenPrice()) {
                greenStockList.add(eachStockUS);
            }
        }
        return greenStockList;
    }

    @Override
    public List<StockUS> find10MostTradedVolStock() {

        List<StockUS> rawStockList = stockRepository.findAll();

        // TreeMap is sorted Map by value
        SortedMap<Long, Float> mostTradedStockMap = new TreeMap<>();

        List<StockUS> mostTradedVolStock = new ArrayList<>();

        for(StockUS eachStock : rawStockList) {
            Float tradedCap = (eachStock.getTradingVol() * eachStock.getAveragePrice()) / 100000;
            mostTradedStockMap.put(eachStock.getId(), tradedCap);
        }

        Map<Long, Float> sortedTradedStockMap = descendingSortByValue(mostTradedStockMap);
        logger.error("Khaaaa  tradingVol = " + sortedTradedStockMap);


        int VolNum = 10;

        for(Map.Entry<Long, Float> stock : sortedTradedStockMap.entrySet()) {
               for(StockUS s : rawStockList) {
                   if(s.getId().equals(stock.getKey())) {
                       logger.error("Khaaaa  tradingVol = " + stock.getValue());
                       mostTradedVolStock.add(s);
                }
             }
            VolNum--;
            if(VolNum == 0) break;
        }

        return mostTradedVolStock;
    }

    @Override
    public List<StockUS> find10MostGreenStock() {
        List<StockUS> greenStockList = findGreenStock();

        // TreeMap is sorted Map by value
        Map<Long, Float> mostGreenStockMap = new HashMap<>();

        List<StockUS> mostGreenStock = new ArrayList<>();

        for(StockUS eachStockUS : greenStockList) {
            Float raisePercent = ((eachStockUS.getClosePrice() - eachStockUS.getOpenPrice()) / eachStockUS.getOpenPrice()) * 100;
            mostGreenStockMap.put(eachStockUS.getId(), raisePercent);
        }

        Map<Long, Float> sortedRedMap = descendingSortByValue(mostGreenStockMap);

        int greenNum = 10;

        for(Map.Entry<Long, Float> stock : sortedRedMap.entrySet()) {
            for(StockUS s : greenStockList) {
                if(s.getId().equals(stock.getKey())) {
                    logger.error("Khaaa raisePercent = " + stock.getValue());
                    mostGreenStock.add(s);
                }
            }
            greenNum--;
            if(greenNum == 0) break;
        }

        return mostGreenStock;
    }

    @Override
    public List<StockUS> find10MostRedStock() {
        List<StockUS> redStockList = findRedStock();

        // TreeMap is sorted Map by value
        Map<Long, Float> mostRedStockMap = new HashMap<>();

        List<StockUS> mostRedStock = new ArrayList<>();

        for(StockUS eachStockUS : redStockList) {
            Float raisePercent = ((eachStockUS.getOpenPrice() - eachStockUS.getClosePrice()) / eachStockUS.getOpenPrice()) * 100;
            mostRedStockMap.put(eachStockUS.getId(), raisePercent);
        }

        Map<Long, Float> sortedRedMap = descendingSortByValue(mostRedStockMap);

        logger.error("Khaaa lostPercent = " + sortedRedMap);

        int redNum = 10;

        for(Map.Entry<Long, Float> stock : sortedRedMap.entrySet()) {
            for(StockUS s : redStockList) {
                if(s.getId().equals(stock.getKey())) {
                    logger.error("Khaaa lostPercent = " + stock.getValue());
                    mostRedStock.add(s);
                }
            }
            redNum--;
            if(redNum == 0) break;
        }

        return mostRedStock;
    }
}

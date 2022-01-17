package com.pmkha.stockdata;

import com.pmkha.stockdata.fetching.RawStockData;
import com.pmkha.stockdata.model.StockUS;
import com.pmkha.stockdata.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class StockDataApplication implements CommandLineRunner {

	@Autowired
	StockRepository stockRepository;


	public static void main(String[] args) {
		SpringApplication.run(StockDataApplication.class, args);
		System.out.println("Start Web server!!!");
	}

	@Override
	public void run(String... args) {
		Logger logger = LoggerFactory.getLogger(StockDataApplication.class);

		RawStockData rawStockData = new RawStockData();

		LocalDate dateObj = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
		String date = dateObj.format(formatter);

		List<StockUS> rawStockList;

		try {
			rawStockList = rawStockData.getAllStockItemData("2022-01-11");

			/**
			logger.debug("Khaaaaaaaaaaaaaa " + rawStockList.size());
			logger.debug("Khaaaaaaaaaaaaaa " + rawStockList);
			 **/

			// Delete old records of table before storing new one
			stockRepository.deleteAll();

			for(StockUS eachStock : rawStockList) {
				stockRepository.save(eachStock);
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}

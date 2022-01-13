package com.pmkha.stockdata.fetching;

import com.pmkha.stockdata.StockDataApplication;
import com.pmkha.stockdata.model.StockUS;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RawStockData {
    private Logger logger = LoggerFactory.getLogger(StockDataApplication.class);

    private RestTemplate restTemplate;

    private JSONParser jsonParser = new JSONParser();

    private List<StockUS> stockList = new ArrayList<>();

    public List<StockUS> getAllStockItemData(String date) throws URISyntaxException {

        restTemplate = new RestTemplate();

        final String baseUrl = "https://api.polygon.io/v2/aggs/grouped/locale/us/market/stocks/" + date + "?adjusted=true&apiKey=qlsYxMOU9cv5BprU26PUR4lnv5wt9Y09";
        URI uri = new URI(baseUrl);

        String result = restTemplate.getForEntity(uri, String.class).getBody();

        int limitStockRequest = 1;

        try {
            JSONObject rawStockJsonObject = (JSONObject) jsonParser.parse(result);
            JSONArray stockJsonArrayList = (JSONArray) rawStockJsonObject.get("results");
            Iterator<StockUS> iterator = stockJsonArrayList.iterator();

            while(iterator.hasNext() && limitStockRequest <= 10000) {
                StockUS stockUS = new StockUS();

                JSONObject forEachStockObject = (JSONObject) jsonParser.parse(String.valueOf(iterator.next()));

                stockUS.setSymbol(forEachStockObject.get("T").toString());
                stockUS.setClosePrice(Float.parseFloat(forEachStockObject.get("c").toString()));
                stockUS.setHighestPrice(Float.parseFloat(forEachStockObject.get("h").toString()));
                stockUS.setLowestPrice(Float.parseFloat(forEachStockObject.get("l").toString()));
                stockUS.setOpenPrice(Float.parseFloat(forEachStockObject.get("o").toString()));
                stockUS.setTradingVol(Float.parseFloat(forEachStockObject.get("v").toString()));
                stockUS.setNoTransaction(Long.parseLong(forEachStockObject.get("n").toString()));
                stockUS.setAveragePrice(Float.parseFloat(forEachStockObject.get("vw").toString()));
                stockUS.setStartTimestamp(Long.parseLong(forEachStockObject.get("t").toString()));

                // Create a current stock list
                stockList.add(stockUS);

                limitStockRequest++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stockList;
    }
}

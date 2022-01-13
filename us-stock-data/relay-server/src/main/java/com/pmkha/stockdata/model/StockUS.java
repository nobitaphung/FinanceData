package com.pmkha.stockdata.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class StockUS {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String symbol;

    private Float closePrice;

    private Float highestPrice;

    private Float lowestPrice;

    private Float openPrice;

    private Long noTransaction;

    private Float tradingVol;

    private Long startTimestamp;

    private Float averagePrice;

    public StockUS() {

    }

    public StockUS(String symbol, Float closePrice, Float highestPrice, Float lowestPrice, Float openPrice, Long noTransaction, Float tradingVol, Long startTimestamp, Float averagePrice) {
        this.symbol = symbol;
        this.closePrice = closePrice;
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.openPrice = openPrice;
        this.noTransaction = noTransaction;
        this.tradingVol = tradingVol;
        this.startTimestamp = startTimestamp;
        this.averagePrice = averagePrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public Long getId() {
        return id;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Float closePrice) {
        this.closePrice = closePrice;
    }

    public Long getNoTransaction() {
        return noTransaction;
    }

    public void setNoTransaction(Long noTransaction) {
        this.noTransaction = noTransaction;
    }

    public Float getTradingVol() {
        return tradingVol;
    }

    public void setTradingVol(Float tradingVol) {
        this.tradingVol = tradingVol;
    }

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Float getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Float averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Float getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(Float highestPrice) {
        this.highestPrice = highestPrice;
    }

    public Float getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Float lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public Float getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Float openPrice) {
        this.openPrice = openPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockUS stockUS = (StockUS) o;
        return Objects.equals(id, stockUS.id) && Objects.equals(symbol, stockUS.symbol) && Objects.equals(closePrice, stockUS.closePrice) && Objects.equals(highestPrice, stockUS.highestPrice) && Objects.equals(lowestPrice, stockUS.lowestPrice) && Objects.equals(openPrice, stockUS.openPrice) && Objects.equals(noTransaction, stockUS.noTransaction) && Objects.equals(tradingVol, stockUS.tradingVol) && Objects.equals(startTimestamp, stockUS.startTimestamp) && Objects.equals(averagePrice, stockUS.averagePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, closePrice, highestPrice, lowestPrice, openPrice, noTransaction, tradingVol, startTimestamp, averagePrice);
    }

    @Override
    public String toString() {
        return "StockUS{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", closePrice=" + closePrice +
                ", highestPrice=" + highestPrice +
                ", lowestPrice=" + lowestPrice +
                ", openPrice=" + openPrice +
                ", noTransaction=" + noTransaction +
                ", tradingVol=" + tradingVol +
                ", startTimestamp=" + startTimestamp +
                ", averagePrice=" + averagePrice +
                '}';
    }
}

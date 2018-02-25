package com.example.anandpatelak.anandpatel_comp304finallabtest_002;

/**
 * Created by anandpatelak on 2018-01-11.
 */

public class Stock {
    private int stockQuote;
    private String stockSymbol;
    private String companyName;


    public Stock() {

    }

    public Stock(String stockSymbol, String companyName, int stockQuote) {
        this.stockSymbol = stockSymbol;
        this.companyName = companyName;
        this.stockQuote = stockQuote;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getStockQuote() {
        return stockQuote;
    }

    public void setStockQuote(int stockQuote) {
        this.stockQuote = stockQuote;
    }
}

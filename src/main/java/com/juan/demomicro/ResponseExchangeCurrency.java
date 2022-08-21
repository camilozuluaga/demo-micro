package com.juan.demomicro;

public class RespuestaExchangeCurrency {

    private double rate;
    private double result;
    private double amount;
    private String from;
    private String to;

    public RespuestaExchangeCurrency() {
    }

    public RespuestaExchangeCurrency(double rate, double result, double amount, String from, String to) {
        this.rate = rate;
        this.result = result;
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    public double getResult() {
        return result;
    }
    public void setResult(double result) {
        this.result = result;
    }

    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}

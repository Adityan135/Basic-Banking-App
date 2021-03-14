package com.example.aditya.basicbankingapp;

/**
 * Created by Aditya on 13-03-2021.
 */

public class Transaction {
    private String name1;
    private String name2;
    private String status;
    private String amount;

    public Transaction(String name1, String name2, String status, String amount) {
        this.name1 = name1;
        this.name2 = name2;
        this.status = status;
        this.amount = amount;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

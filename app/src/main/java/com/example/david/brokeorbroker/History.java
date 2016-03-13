package com.example.david.brokeorbroker;

/**
 * Created by David on 3/11/2016.
 */
public class History {
    private String company;
    private String date;

    public History(String date,String company){
        this.setDate(date);
        this.setCompany(company);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return company;
    }
}


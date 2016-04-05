package com.example.david.brokeorbroker;

/**
 * Created by David on 3/9/2016.
 */
public class Favorite {
    private String company;
    private String percent_change;

    public Favorite(String percent_change,String company){
        this.setPercent_change(percent_change);
        this.setCompany(company);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPercent_change() {
        return percent_change;
    }

    public void setPercent_change(String percent_change) {
        this.percent_change = percent_change;
    }
}

package com.udemy.filmdemo.topmovies;

/**
 * Created by chen on 2017/2/17.
 */

public class ViewModel {

    private String name;
    private String country;

    public ViewModel(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

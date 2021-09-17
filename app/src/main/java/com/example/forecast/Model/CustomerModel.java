package com.example.forecast.Model;

public class CustomerModel implements CustomerInterface {
    private String name;
    private boolean snow;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean willSnow() {
        return false;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}

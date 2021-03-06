package com.kempo.easyride.model;

import java.util.ArrayList;
import java.util.List;

public class Rider extends Person {

    private Car current = null; // their current car
    private final List<Driver> preferences = new ArrayList<Driver>();

    public Rider(String n, String a) {
        super();
        this.name = n;
        this.address = a;
    }

    public Car getCurrentCar() {
        return current;
    }

    public void setCar(Car c) {
        current = c;
    }

    public List<Driver> getRiderPreferences() {
        return preferences;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(name + " (" + address + ")");

        return sb.toString();
    }
}

package com.company;

public class Amperometer extends Resistor implements Meter {
    public Amperometer(String name) {
        super(name,0);
    }

    public Amperometer(String name,Resistor resistor1, Resistor resistor2) {
        super(name,0, resistor1, resistor2);
    }

    @Override
    public double getInfo() {
        return getCurrent();
    }
}
/* just nothing*/
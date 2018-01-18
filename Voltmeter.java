package com.company;

public class Voltmeter extends Resistor implements Meter {
    final double OPEN_RESISTANCE=999999999; //infintie
    public Voltmeter(String name) {
        super(name,0);
        setResistance(OPEN_RESISTANCE);
    }

    public Voltmeter(String name,Resistor resistor1, Resistor resistor2) {
        super(name,0, resistor1, resistor2);
        setResistance(0);
    }

    @Override
    public double getInfo() {
        return getVoltage();
    }
}

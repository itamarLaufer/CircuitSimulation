package com.company;

/**
 * represents a voltmeter which is kind of resistor (with enormous resistance) and a Meter that measures voltage on it
 */
public class Voltmeter extends Resistor implements Meter {
    final double OPEN_RESISTANCE=999999999; //infintie

    /**
     * creates a voltmeter with the given data
     * @param name the name of the voltmeter
     */
    public Voltmeter(String name) {
        super(name,0);
        setResistance(OPEN_RESISTANCE);
    }

    /**
     * method gives the value shown on the voltmeter display
     * @return the voltage on the voltmeter
     */
    @Override
    public double getInfo() {
        return getVoltage();
    }
    /**
     * overrides setResistance because this kind of resisotr doesn't allow changing it's resistance (it can be changed by changing other data like it's length)
     * @param resistance
     */
    @Override
    public void setResistance(double resistance)
    {
        //do nothing
    }
}

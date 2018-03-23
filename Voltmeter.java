package com.company;

/**
 * represents a voltemeter which is kind of resistor (with enourmous resistence) and a Meter that measures voltage on it
 */
public class Voltmeter extends Resistor implements Meter {
    final double OPEN_RESISTANCE=999999999; //infintie

    /**
     * creates a voltemeter with the given data
     * @param name the name of the voltemeter
     */
    public Voltmeter(String name) {
        super(name,0);
        setResistance(OPEN_RESISTANCE);
    }

    /**
     * creates a voltemeter with the given data
     * @param name the name of the voltemeter
     * @param resistor1 a resistor which is connected to the voltemeter that the constructor builds (null means nothing is connected)
     * @param resistor2 a resistor which is connected to the voltemeter that the constructor builds (null means nothing is connected) if resistor1 is null it must be also null
     */
    public Voltmeter(String name,Resistor resistor1, Resistor resistor2) {
        super(name,0, resistor1, resistor2);
        setResistance(0);
    }

    /**
     * mehod gives the value shown on the voltemeter display
     * @return the voltage on the voltemeter
     */
    @Override
    public double getInfo() {
        return getVoltage();
    }
    /**
     * overrides setResistance because this kind of resisotr doesn't allow changing it's resistence (it can be changed by changing other data like it's length)
     * @param resistance
     */
    @Override
    public void setResistance(double resistance)
    {
        //do nothing
    }
}

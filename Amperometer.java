package com.company;

/**
 * represents an amperometer which is a resistor (with 0 resistence) and a Meter which measures the current that flows through it
 */
public class Amperometer extends Resistor implements Meter {
    /**
     * creates aa amperometer with the given data
     * @param name the name of the amperometer
     */
    public Amperometer(String name) {
        super(name,0);
    }

    /**
     * creates aa amperometer with the given data
     * @param name the name of the amperometer
     * @param resistor1 a resistor which is connected to the amperometer that the constructor builds (null means nothing is connected)
     * @param resistor2 a resistor which is connected to the amperometer that the constructor builds (null means nothing is connected) if resistor1 is null it must be also null
     */
    public Amperometer(String name,Resistor resistor1, Resistor resistor2) {
        super(name,0, resistor1, resistor2);
    }
    /**
     * mehod gives the value shown on the amperometer display
     * @return the current that flow through it
     */
    @Override
    public double getInfo() {
        return getCurrent();
    }
}
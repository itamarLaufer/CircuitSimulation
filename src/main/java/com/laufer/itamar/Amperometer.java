package com.laufer.itamar;

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

    @Override
    public double getInfo() {
        return getCurrent();
    }
}

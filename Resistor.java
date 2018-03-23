package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represents a resistor in a circuit
 */
public class Resistor
{
    private String name;
    private double resistance; //Ω
    private double current; //A
    private double voltage; //V
    /**
     * resistor1 and 2 are the resistors which are connected to the resisotr in 1 side the resistor is probably connect to at least one more resistor in the other side
     */
    protected Resistor resistor1;
    protected Resistor resistor2;

    /**
     * constructor metod creates Resistor with the given parameters
     * @param name the name of the resistor
     * @param resistance the resistence of the resistor in Ω
     */
    public Resistor(String name,double resistance) {
        this.name=name;
        this.resistance = resistance;
        current=0;
        voltage=0;
        resistor1=null; //no resistor is connected
        resistor2=null;
    }

    /**
     * constructor metod creates Resistor with the given parameters
     * @param name the name of the resistor
     * @param resistance the resistence of the resistor in Ω
     * @param resistor1 a resistor which is connected to the resistor that the constructor builds (null means nothing is connected)
     * @param resistor2 a resistor which is connected to the resistor that the constructor builds (null means nothing is connected) if resistor1 is null it must be also null
     */
    public Resistor(String name,double resistance,Resistor resistor1,Resistor resistor2) {
        this(name,resistance);
        this.resistor1=resistor1;
        this.resistor2=resistor2;
    }


    public double getResistance() {
        return resistance;
    }

    /**
     *
     * @return the current in Ampers which pass in the resistor (this data is  filled after this resistor is associated with circuit)
     */
    public double getCurrent() {
        return current;
    }

    /**
     *
     * @return the voltage on the resistor in Voltes (this data is  filled after this resistor is associated with circuit)
     */
    public double getVoltage() {
        return voltage;
    }
    public void setCurrent(double current) {
        this.current = current;
        calculateVoltage();
    }

    /**
     * method calculates the voltage on the resistor after it's associated to a circuit using Owm Law  V =I*R
     */
    public void calculateVoltage()
    {
        this.voltage=current*resistance;
    }

    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    @Override
    public String toString() {
        return "Resistor{" +
                "name='" + name + '\'' +
                ", resistance=" + resistance +"Ω"+
                ", current=" + current +"A"+
                ", voltage=" + voltage +"Ω"+
                ", resistor1=" + resistor1 +
                ", resistor2=" + resistor2 +
                '}';
    }

    /**
     *
     * @return whether this resistor is connected to 2 resistors (after it)
     */
    public boolean split()
    {
        return !(resistor1==null||resistor2==null);
    }

    /**
     *
     * @return whether this resistor is not connected (after it) to any resistor (it means that it's connected to the voltage source)
     */
    public boolean isFinal()
    {
        return (resistor1==null&&resistor2==null);
    }

    /**
     *
     * @return the power in Wats thich is achived in the resistor using p = I*V
     */
    public double getPower()
    {
        return getCurrent()*getVoltage();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * the method connects the given resistor this resistor if possible (it's not already connected to 2 resistors)
     * @param other resistor to connect
     */
    public void connect(Resistor other)
    {
        if(resistor1==null)
            resistor1=other;
        else if(resistor2==null)
            resistor2=other;
    }

}

package com.laufer.itamar.resistors;

import com.laufer.itamar.BinNode;

import java.util.LinkedList;

/**
 * this class represents a resistor in a circuit
 */
public class Resistor
{
    private String name;
    private double resistance; //Ω
    private double current; //A
    private double voltage; //V
    public LinkedList<Character> id;
    public BinNode<Resistor> cut;
    /**
     * resistor1 and 2 are the resistors which are connected to the resistor in 1 side the resistor is probably connect to at least one more resistor in the other side
     */

    /**
     * constructor method creates Resistor
     * @param name the name of the resistor
     * @param resistance the resistance of the resistor in Ω
     */
    public Resistor(String name,double resistance) {
        this.name=name;
        this.resistance = resistance;
        current=0;
        voltage=0;
    }

    public double getResistance() {
        return resistance;
    }

    /**
     *
     * @return the current in Ampere which pass in the resistor (this data is  filled after this resistor is associated with circuit)
     */
    public double getCurrent() {
        return current;
    }

    /**
     *
     * @return the voltage on the resistor in Volt (this data is  filled after this resistor is associated with circuit)
     */
    public double getVoltage() {
        return voltage;
    }
    public void setCurrent(double current) {
        this.current = current;
        calculateVoltage();
    }

    /**
     * method calculates the voltage on the resistor after it's associated to a circuit using Owm Law  V = I*R
     */
    public void calculateVoltage()
    {
        this.voltage = current*resistance;
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
                '}';
    }


    /**
     *
     * @return the power in Wat that is achieved in the resistor using p = I*V
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


}

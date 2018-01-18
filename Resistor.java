package com.company;

import java.util.ArrayList;
import java.util.List;

public class Resistor
{
    private String name;
    private double resistance; //Ω
    private double current; //A
    private double voltage; //V
    protected Resistor resistor1;
    protected Resistor resistor2;

    public Resistor(String name,double resistance) {
        this.name=name;
        this.resistance = resistance;
        current=0;
        voltage=0;
        resistor1=null;
        resistor2=null;
    }
    public Resistor(String name,double resistance,Resistor resistor1,Resistor resistor2) {
        this(name,resistance);
        this.resistor1=resistor1;
        this.resistor2=resistor2;
    }


    public double getResistance() {
        return resistance;
    }

    public double getCurrent() {
        return current;
    }

    public double getVoltage() {
        return voltage;
    }
    public void setCurrent(double current) {
        this.current = current;
    }
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

    public boolean split()
    {
        return !(resistor1==null||resistor2==null);
    }
    public boolean isFinal()
    {
        return (resistor1==null&&resistor2==null);
    }
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
    public void connect(Resistor other)
    {
        if(resistor1==null)
            resistor1=other;
        else if(resistor2==null)
            resistor2=other;
    }

}

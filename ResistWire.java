package com.company;

import java.util.List;

public class ResistWire extends Resistor
{

    private double resistivity;
    private double length;
    private double area;
    /**
     *
     * @param resistivity the wire resistivity [Ωm]
     * @param length the wire length [m]
     * @param area the wire area[m*m]
     */
    public ResistWire(String name,double resistivity, double length, double area)
    {
        super(name,resistivity*length/area);
        this.area=area;
        this.resistivity=resistivity;
        this.length=length;
    }
    public ResistWire(String name,double resistivity, double length, double area , Resistor resistor1, Resistor resistor2)
    {
        super(name,resistivity*length/area,resistor1,resistor2);
    }
    private void calculateResistance()
    {
        setResistance(resistivity*length/area);
    }

    public double getResistivity() {
        return resistivity;
    }

    public void setResistivity(double resistivity) {
        this.resistivity = resistivity;
        calculateResistance();
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
        calculateResistance();
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
        calculateResistance();
    }

    @Override
    public String toString() {
        return super.toString()+" "+"ResistWire{" +
                "resistivity=" + resistivity +"Ωm"+
                ", length=" + length +"m"+
                ", area=" + area +"m*m"+
                '}';
    }
    @Override
    public void setResistance(double resistance)
    {
        //do nothing
    }
}

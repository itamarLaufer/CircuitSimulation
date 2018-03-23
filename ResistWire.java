package com.company;

import java.util.List;

/**
 * represents a wire in a circuit which has some resistence whioch is determined by this equation: resistivity*length/area
 */
public class ResistWire extends Resistor
{

    private double resistivity; //[Ωm]
    private double length; //[m]
    private double area; //[m*m]
    /**
     * creates resist wre with the given data
     * @param name the name of the resistor
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

    /**
     * creates resist wire with the given data
     * @param name the name of the resistor
     * @param resistivity the wire resistivity [Ωm]
     * @param length the wire length [m]
     * @param area the wire area[m*m]
     * @param resistor1 a resistor which is connected to the resist wire that the constructor builds (null means nothing is connected)
     * @param resistor2 a resistor which is connected to the resist wire that the constructor builds (null means nothing is connected) if resistor1 is null it must be also null
     */
    public ResistWire(String name,double resistivity, double length, double area , Resistor resistor1, Resistor resistor2)
    {
        super(name,resistivity*length/area,resistor1,resistor2);
    }

    /**
     * calculate resistence according to the equation: resistivity*length/area
     * metod is called after changing some data about the resist wire
     */
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

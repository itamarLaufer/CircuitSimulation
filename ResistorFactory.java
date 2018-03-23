package com.company;

import java.util.Scanner;

/**
 * a class that concentrate the creation of resistos
 * the class generates the data about the resistors to build using input
 */
public class ResistorFactory
{
    private Scanner in;

    public ResistorFactory() {
        in = new Scanner(System.in);
    }

    /**
     * the class take input from the user about the resistor to create and returns it
     * @return a resistor accordeing to the data about it given in input
     */
    public Resistor getResistor()
    {
        System.out.println("Insert resistor type(regular,switch,amperometer,voltmeter,resistWire)");
        String type = in.next();
        System.out.println("Insert resistor name");
        String name = in.next();
        if(type.equals("regular"))
        {
            System.out.println("Insert amount of resistance(Ω)");
            return new Resistor(name,in.nextDouble());
        }
        else if(type.equals("switch"))
        {
            System.out.println("Is it open? (true=open, false=close)");
            return new Switch(name,in.nextBoolean());
        }
        else if(type.equals("amperometer"))
        {
            return new Amperometer(name);
        }
        else if(type.equals("voltmeter"))
        {
            return new Voltmeter(name);
        }
        else if(type.equals("resist wire"))
        {
            System.out.println("Insert amount of resistivity(Ωm)");
            double resitvity = in.nextDouble();
            System.out.println("Insert wire's length(m)");
            double length = in.nextDouble();
            System.out.println("Insert wire's area(m*m)");
            return new ResistWire(name,resitvity,length,in.nextDouble());
        }
        else
            System.out.println("We do not support this type! creation has stopped");
            return null;
    }
}

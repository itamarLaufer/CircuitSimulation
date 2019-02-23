package com.laufer.itamar;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static List<BinNode<Resistor>> resistors;
    public static void main(String[] args) {
        resistors = new ArrayList<>(10);
        BinNode<Resistor> positiveTrigger = new BinNode<>(new Resistor("positiveTrigger",0));
        BinNode<Resistor> negativeTrigger = new BinNode<>(new Resistor("negativeTrigger",0));
        BinNode<Resistor> r1 = new BinNode<>(new Resistor("r1" ,10));
        BinNode<Resistor> r2 = new BinNode<>(new Resistor("r2" ,8));
        BinNode<Resistor> r3 = new BinNode<>(new Resistor("r3" ,5));
        BinNode<Resistor> r4 = new BinNode<>(new Resistor("r4" ,20));
        positiveTrigger.connect(r1);
        r1.connect(r2);
        r1.connect(r4);
        r2.connect(r3);
        r3.connect(negativeTrigger);
        r4.connect(negativeTrigger);
        resistors.add(r1);
        resistors.add(r2);
        resistors.add(r3);
        resistors.add(r4);
        resistors.add(positiveTrigger);
        resistors.add(negativeTrigger);
        Circuit circuit = new Circuit(20, positiveTrigger, negativeTrigger, resistors);
        for(BinNode<Resistor> r: resistors){
            System.out.println(r);
        }




        /*
        ResistorFactory factory = new ResistorFactory();
        System.out.println("In our system you can create circuits and view the the data about the components");
        System.out.println("In our system every component can be connected to maximum 2 other components.s");
        System.out.println("Insert num of components (not include the voltage source that we create for you.)");
        int numOfComponents= in.nextInt();
        resistors=new ArrayList<>(numOfComponents);
        for(int i=0;i<numOfComponents;i++)
        {
            resistors.add(factory.getResistor());
        }
        System.out.println("Now lets connect the components.");
        System.out.println("We created for you 2 components - 'positiveTrigger' and 'negativeTrigger'");
        System.out.println("positiveTrigger starts the circuit and negativeTrigger ends it. Inner resistance is expressed like normal resistor");
        Resistor r1;
        Resistor r2;
        Resistor positiveTrigger = new Resistor("positiveTrigger",0);
        Resistor negativeTrigger = new Resistor("negativeTrigger",0);
        resistors.add(positiveTrigger);
        resistors.add(negativeTrigger);
        while(true)
        {
            System.out.println("Insert the name of the resistor that you want to connect it with other (Don't connect both sides! There is a meaning to the order!)");
            r1 = findResistorByName(in.next());
            System.out.println("Insert the name of the resistor that you want to connect it with the previous resistor (Don't connect both sides! There is a meaning to the order!)");
            r2 = findResistorByName(in.next());
            r1.connect(r2);
            System.out.println("Insert 'stop' in order to stop connection");
            if(in.next().equals("stop"))
                break;
        }
        System.out.println("Insert emp of voltage source (V)");
        Circuit circuit = new Circuit(in.nextDouble(),positiveTrigger,negativeTrigger,resistors);
        circuit.toString(); */
    }
    public static BinNode<Resistor> findResistorByName(String name)
    {
        for(BinNode<Resistor> resistor:resistors)
        {
            if(resistor.getValue().getName().equals(name))
                return resistor;
        }
        return null;
    }
}

package com.laufer.itamar;

import com.laufer.itamar.resistors.Resistor;
import com.laufer.itamar.resistors.ResistorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
    static Scanner in = new Scanner(System.in);
    static List<BinNode<Resistor>> resistors;

    public static void main(String[] args) {

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
        BinNode<Resistor> r1;
        BinNode<Resistor> r2;
        BinNode<Resistor> positiveTrigger = new BinNode<>(new Resistor("positiveTrigger",0));
        BinNode<Resistor> negativeTrigger = new BinNode<>(new Resistor("negativeTrigger",0));
        resistors.add(positiveTrigger);
        resistors.add(negativeTrigger);
        String input;
        while(true)
        {
            System.out.println("Insert the name of the resistor that you want to connect it with other (Don't connect both sides! There is a meaning to the order!) enter 'stop' in order to stop");
            input = in.next();
            if(input.equals("stop"))
                break;
            r1 = findResistorByName(input);
            System.out.println("Insert the name of the resistor that you want to connect it with the previous resistor (Don't connect both sides! There is a meaning to the order!)");
            r2 = findResistorByName(in.next());
            r1.connect(r2);
        }
        System.out.println("Insert the voltage source (V)");
        Circuit circuit = new Circuit(in.nextDouble() ,positiveTrigger, negativeTrigger, resistors);
        circuit.setCircuitValues();
        System.out.println(circuit);
    }

    public static BinNode<Resistor> findResistorByName(String name) {
        for (BinNode<Resistor> resistor : resistors) {
            if (resistor.getValue().getName().equals(name))
                return resistor;
        }
        return null;
    }
}

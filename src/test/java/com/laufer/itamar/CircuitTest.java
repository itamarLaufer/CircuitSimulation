package com.laufer.itamar;

import com.laufer.itamar.resistors.Resistor;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircuitTest {

    @Test
    public void setCircuitValues() {
    }

    @Test
    public void simpleMakeCuts(){
        BinNode<Resistor>n1 = new BinNode<>(new Resistor("n1", 0));
        BinNode<Resistor>n2 = new BinNode<>(new Resistor("n2", 0));
        BinNode<Resistor>n3 = new BinNode<>(new Resistor("n3", 0));
        BinNode<Resistor>n4 = new BinNode<>(new Resistor("n4", 0));
        BinNode<Resistor>n5 = new BinNode<>(new Resistor("n5", 0));
        BinNode<Resistor>n6 = new BinNode<>(new Resistor("n6", 0));
        n1.connect(n2);
        n1.connect(n3);
        n2.connect(n5);
        n3.connect(n4);
        n4.connect(n5);
        n5.connect(n6);
        Circuit circuit = new Circuit(0, n1, n6, null);
        circuit.setCircuitValues();
        assertEquals(n1.getValue().cut, n5);
    }
    @Test
    public void complexMakeCuts(){
        BinNode<Resistor>n1 = new BinNode<>(new Resistor("n1", 0));
        BinNode<Resistor>n2 = new BinNode<>(new Resistor("n2", 0));
        BinNode<Resistor>n3 = new BinNode<>(new Resistor("n3", 0));
        BinNode<Resistor>n4 = new BinNode<>(new Resistor("n4", 0));
        BinNode<Resistor>n5 = new BinNode<>(new Resistor("n5", 0));
        BinNode<Resistor>n6 = new BinNode<>(new Resistor("n6", 0));
        BinNode<Resistor>n7 = new BinNode<>(new Resistor("n7", 0));
        BinNode<Resistor>n8 = new BinNode<>(new Resistor("n8", 0));
        BinNode<Resistor>n9 = new BinNode<>(new Resistor("n9", 0));
        BinNode<Resistor>n10 = new BinNode<>(new Resistor("n10", 0));
        BinNode<Resistor>n11 = new BinNode<>(new Resistor("n11", 0));
        BinNode<Resistor>n12 = new BinNode<>(new Resistor("n12", 0));
        BinNode<Resistor>n13 = new BinNode<>(new Resistor("n13", 0));
        BinNode<Resistor>n14 = new BinNode<>(new Resistor("n14", 0));
        BinNode<Resistor>n15 = new BinNode<>(new Resistor("n15", 0));

        n1.connect(n2);
        n1.connect(n3);
        n2.connect(n15);
        n15.connect(n4);
        n4.connect(n9);
        n4.connect(n10);
        n9.connect(n11);
        n10.connect(n11);
        n3.connect(n5);
        n3.connect(n6);
        n5.connect(n8);
        n6.connect(n7);
        n7.connect(n8);
        n8.connect(n12);
        n8.connect(n14);
        n12.connect(n13);
        n13.connect(n11);
        n14.connect(n11);

        Circuit circuit = new Circuit(0, n1, n11, null);
        circuit.setCircuitValues();
        assertEquals(n1.getValue().cut, n11);
        assertEquals(n4.getValue().cut, n11);
        assertEquals(n8.getValue().cut, n11);
        assertEquals(n3.getValue().cut, n8);
    }
}
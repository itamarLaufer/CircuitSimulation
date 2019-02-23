package com.laufer.itamar;

import com.laufer.itamar.resistors.Resistor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    @Test
    public void simpleCircuitCalculation(){
        List<BinNode<Resistor>> resistors = new ArrayList<>(10);
        BinNode<Resistor> positiveTrigger = new BinNode<>(new Resistor("positiveTrigger", 0));
        BinNode<Resistor> negativeTrigger = new BinNode<>(new Resistor("negativeTrigger", 0));
        BinNode<Resistor> r1 = new BinNode<>(new Resistor("r1", 10));
        BinNode<Resistor> r2 = new BinNode<>(new Resistor("r2", 8));
        BinNode<Resistor> r3 = new BinNode<>(new Resistor("r3", 5));
        BinNode<Resistor> r4 = new BinNode<>(new Resistor("r4", 20));
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
        circuit.setCircuitValues();

        assertTrue(equalsApproximately(circuit.getTotalCurrent(), 1.12));
        assertTrue(equalsApproximately(circuit.getTotalResistance(), 17.88));
        assertTrue(equalsApproximately(negativeTrigger.getValue().getCurrent(), circuit.getTotalCurrent()));

        assertTrue(equalsApproximately(r1.getValue().getCurrent(), circuit.getTotalCurrent()));
        assertTrue(equalsApproximately(r1.getValue().getVoltage(), r1.getValue().getCurrent() * r1.getValue().getResistance()));

        assertTrue(equalsApproximately(r2.getValue().getCurrent(), 0.68));
        assertTrue(equalsApproximately(r2.getValue().getVoltage(), r2.getValue().getCurrent() * r2.getValue().getResistance()));

        assertTrue(equalsApproximately(r3.getValue().getCurrent(), r2.getValue().getCurrent()));
        assertTrue(equalsApproximately(r3.getValue().getVoltage(), r3.getValue().getCurrent() * r3.getValue().getResistance()));

        assertTrue(equalsApproximately(r4.getValue().getCurrent(), r1.getValue().getCurrent() - r2.getValue().getCurrent()));
        assertTrue(equalsApproximately(r4.getValue().getVoltage(), r4.getValue().getCurrent() * r4.getValue().getResistance()));
    }

    /**
     * Helper function to check if two double numbers are approximately equal.
     * @param num1 first double
     * @param num2 second double
     * @return whether the given doubles are approximately equal
     */
    public static boolean equalsApproximately(double num1, double num2){
        final double DEVIATION = 0.01;
        return Math.abs(num1 - num2) < DEVIATION;
    }
}
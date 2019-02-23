package com.laufer.itamar;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * represents a circuit which has some voltage source and many resistors connected to each other
 */
public class Circuit
{
    private double voltage; //V the voltage of the whole circuit
    private BinNode<Resistor> startResistor; //the first resistor in the circuit which is connected to the positive side of the voltage source
    private BinNode<Resistor> endResistor; //the last resistor in the circuit which is connected to the negative side of the voltage source
    private List<BinNode<Resistor>>components; //the different resistors in the circuit (used for printing all the values in the circuit

    /**
     * create a circuit with the given data
     * @param voltage the voltage of the voltage source in the circuitin Voltes
     * @param resistorPositive the resistor which is connected to the positive side of the voltage source
     * @param resistorNegative the resistor which is connected to the negative side of the voltage source
     * @param components the different resistors in the circuit
     */
    public Circuit(double voltage, BinNode<Resistor> resistorPositive, BinNode<Resistor> resistorNegative, List<BinNode<Resistor>> components) {
        this.components = components;
        this.voltage = voltage;
        this.startResistor = resistorPositive;
        this.endResistor = resistorNegative;
        setCircuitValues();
    }

    /**
     *
     * @return the voltage of the source code in the circuit
     */
    public double getVoltage() {
        return voltage;
    }

    /**
     * updates the voltage of the source and all the resistors in the circuit consequently
     * @param voltage the new voltage of the sorce in the ciruit
     */
    public void setVoltage(double voltage) {
        this.voltage = voltage;
        setCircuitValues();
    }

    /**
     * calculate residence of a specific part in circuit (recursive)
     * @param from the first resistor in the specific part
     * @param to the last resistor in the specific part
     * @return the equivalent resistance of the part between fran and to
     */
    private double getResistanceOfPart(BinNode<Resistor> from, BinNode<Resistor> to)
    {
        if(from == to) //stop condition we reached the end of the part
            return 0;
        if(from.getRight() == null && from.getLeft() != null) //only one resistor is connected
            return from.getValue().getResistance() + getResistanceOfPart(from.getLeft(), to); //from resistance is added to the result
        // if the from has 2 resistors connected
        BinNode<Resistor> cut = from.getValue().cut;
        double resistance1 = getResistanceOfPart(from.getLeft(), cut); //resistance of 1 side of the split
        double resistance2 = getResistanceOfPart(from.getRight(), cut); //resistance of other side of the split
        //resistance will be the resistance of from plus the equivalent of the split (r1*r2/(r1+r2) plus the resistance to 'to')
        return from.getValue().getResistance() + resistance1 * resistance2 / (resistance1 + resistance2) + getResistanceOfPart(cut, endResistor);
    }

    /**
     * @return the equivalent resistance of the entire circuit
     */
    public double getTotalResistance()
    {
        return getResistanceOfPart(startResistor, endResistor);
    }

    /**
     * @return the total current in the circuit using Own Law
     */
    public double getTotalCurrent()
    {
        return voltage / getTotalResistance();
    }

    /**
     * method pass through the circuit from the given node and update the current (and the voltage consequently which flows through each resistor in circuit (recursive)
     * @param node the node which has the given current and from it we are calculating
     * @param current the current the the given node gets
     */
    private void setCircuitValues(BinNode<Resistor> node, double current)
    {
        if(node == null)
            return;
        node.getValue().setCurrent(current);
        if(node == endResistor)
            return;
        if(node.getRight() == null && node.getLeft() != null) //linear connection
        {
            //the next node gets all the current that the last node got
            setCircuitValues(node.getLeft(), current); //continue from the next node
        }

        else {
            BinNode<Resistor> cut = node.getValue().cut;
            double resistance1 = getResistanceOfPart(node.getLeft(), cut); //the resistance of 1 side of split
            double resistance2 = getResistanceOfPart(node.getRight(), cut); //the resistance of other side of split
            // current each side of split get is in reverse ratio to the resistances of each part
            double current1 = current * (resistance2 / (resistance1 + resistance2));
            double current2 = current * (resistance1 / (resistance1 + resistance2));
            // continue calculating for each side of split
            setCircuitValues(node.getLeft(), current1);
            setCircuitValues(node.getRight(), current2);
            // after cut all the current is united again
            cut.getValue().setCurrent(current);
            //continue calculating from the cut node
            setCircuitValues(cut, current);
        }
    }

    /**
     * pass through the circuit from the start resistor to the end resitor and update the current (and the voltage consequently) which flows through each resistor in circuit
     */
    public void setCircuitValues()
    {
        LinkedList<Character>firstId = new LinkedList<>();
        firstId.add('S');
        updateCuts(startResistor, firstId, new LinkedList<>());
        double totalCurrent = getTotalCurrent();
        setCircuitValues(startResistor, totalCurrent);

    }
    @Override
    public String toString()
    {
        StringBuilder res= new StringBuilder();
        res.append("Circuit:voltage=").append(voltage).append("V");
        res.append(System.lineSeparator());
        res.append("Components:").append(System.lineSeparator());
        for(BinNode<Resistor> resistor:components)
        {
            res.append(resistor.toString());
            res.append(System.lineSeparator());
        }
        res.append("]");
        return res.toString();
    }

    /**
     * Pass through the circuit and update for each node where there is a split its cut node when they meet again
     * @param node the current node
     * @param id the current id of the node which represents the way from the root node to the current node.
     * S means single child connection, L means left child and R a right chile, this id helps finding the cut node for each split
     * @param splitting list of the nodes found yet that splits (has 2 children)
     */
    private void updateCuts(BinNode<Resistor>node, LinkedList<Character>id, LinkedList<BinNode<Resistor>>splitting){
        if(node == null) {
            return;
        }
        if(node.getValue().id != null){ //we've passed this resistor before which means that there is a meeting of a split here
            List<Character> sharedParentId = findSharedId(new LinkedList<>(id), node.getValue().id);
            BinNode<Resistor> sharedParent = splitting.stream().filter(it -> it.getValue().id.hashCode() == sharedParentId.hashCode()).collect(Collectors.toList()).get(0);
            sharedParent.getValue().cut = node;
            node.getValue().id = id;
        }
        else{
            node.getValue().id = id;
            if(node.getRight() == null && node.getLeft() != null) { //only one connected
                LinkedList<Character>newId = new LinkedList<>(id);
                newId.add('S');
                updateCuts(node.getLeft(), newId, splitting);
            }
            else if(node.getRight() != null){ //2 connected
                LinkedList<Character>newId1 = new LinkedList<>(id);
                LinkedList<Character>newId2 = new LinkedList<>(id);
                newId1.add('L');
                newId2.add('R');
                splitting.add(node);
                updateCuts(node.getLeft(), newId1, splitting);
                updateCuts(node.getRight(), newId2, splitting);
                splitting.removeLast();
            }
        }
    }

    /**
     * Finds the biggest shared id of the 2 given ids.
     * A shared id is the longest sequence of characters from the beginning that is the same in both string ids.
     * For instance SLLSSLLRRLLS and SLLSRRSSLLR will return SLLS
     * @param id1
     * @param id2
     */
    private List<Character> findSharedId(LinkedList<Character> id1, LinkedList<Character> id2) {
        int size1 = id1.size();
        int size2 = id2.size();
        int delta = size1 -size2;
        LinkedList<Character> bigger;
        if(delta > 0)
            bigger = id1;
        else {
            bigger = id2;
            delta *= -1;
        }
        for(int i=0; i < delta; i++){
            bigger.removeLast();
        }
        while (!id1.isEmpty() && id1.hashCode() != id2.hashCode()){
            id1.removeLast();
            id2.removeLast();
        }
        return id1;
    }
}

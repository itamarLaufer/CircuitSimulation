package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * represents a circuit which has some voltage source and many resistors connected to each other
 */
public class Circuit
{
    private double voltage; //V the voltage of the whole circuit
    private Resistor startResistor; //the first resistor in the circuit which is connected to the positive side of the voltage source
    private Resistor endResistor; //the last resistor in the circuit which is connected to the negative side of the voltage source
    private List<Resistor>resistors; //the different resistors in the circuit (used for printing all the values in the circuit

    //Todo resistors will be initiallized from the start and end resistors and not from parameter

    /**
     * create a circuit with the given data
     * @param voltage the voltage of the voltage source in the circuitin Voltes
     * @param resistorPositive the resistor which is connected to the positive side of the voltage source
     * @param resistorNegative the resistor which is connected to the negative side of the voltage source
     * @param resistors the different resistors in the circuit
     */
    public Circuit(double voltage,Resistor resistorPositive,Resistor resistorNegative,List<Resistor>resistors) {
        this.resistors=resistors;
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
     * calculate resstence of a specific part in circuit (recursive)
     * @param from the first resistor in the specific part
     * @param to the last resistor in the specific part
     * @return the equivalent resistence of the part between fron and to
     */
    private double getResistanceOfPart(Resistor from,Resistor to)
    {
        if(from==to) //stop codition we reached the end of the part
            return 0;
        if(from.resistor2==null&&from.resistor1!=null) //only one resistor is connected
            return from.getResistance()+getResistanceOfPart(from.resistor1,to); //from resistence is added to result
        // if the from has 2 resistors connected
        Resistor cut = findCut(from.resistor1,from.resistor2); //cut is the resistor where the 2 resistor splitting from 'from' are getting united again
        double resistance1 = getResistanceOfPart(from.resistor1,cut); //resistence of 1 side of the split
        double resistance2 = getResistanceOfPart(from.resistor2,cut); //resistence of other side of the split
        //resistence will be the resistence of from plus the equivalent of the split (r1*r2/(r1+r2) plus the resistence to 'to')
        return from.getResistance()+resistance1*resistance2/(resistance1+resistance2)+getResistanceOfPart(cut,endResistor);
    }

    /**
     *
     * @return the equivalant resistence of the entire circuit
     */
    public double getTotalResistance()
    {
        return getResistanceOfPart(startResistor,endResistor);
    }

    /**
     * finds the resistor where r1 and r2 meets each other
     * @param r1
     * @param r2
     * @return
     */
    public Resistor findCut(Resistor r1,Resistor r2)
    {
        List<Resistor>l1=resistorsGroupToList(r1); //resistor connected to r1 and to resistors after it
        List<Resistor>l2=resistorsGroupToList(r2); //resistor connected to r2 and to resistors after it
        for(int i=0;i<l1.size();i++)
        {
            for(int j=0;j<l2.size();j++)
            {
                if(l2.get(j)==l1.get(i)) //same resistor
                    return l2.get(j);
            }
        }
        return endResistor; //they meet only in the end of the circuit
    }

    /**
     * genereate a list of resistors connected (not only directly but also connected to other which are connected) to the given resistor
     * @param resistor the resistor to genreate resistors connected to it
     * @return list of the resistors which are connected (not only directly but also connected to other which are connected) to the given resistor
     */
    public List<Resistor>resistorsGroupToList(Resistor resistor)
    {
        List<Resistor>res = new ArrayList<>();
        addResistorsGroupToList(resistor,res);
        return res;
    }

    /**
     * method insert the given resistor and the resistors connected to it to the given list (recursive)
     * @param resistor the resistor to insert it and the resistors connect to it (not only directly etc) to the given list
     * @param list to insert to it the given resistor and those connected to it.
     */
    private void addResistorsGroupToList(Resistor resistor, List<Resistor> list) {
        if(resistor==null)
            return;
        if (resistor != endResistor) {
            list.add(resistor);
            addResistorsGroupToList(resistor.resistor1, list);
            addResistorsGroupToList(resistor.resistor2, list);
        }
    }

    /**
     *
     * @return the total current in the circuit using Own Law
     */
    public double getTotalCurrent()
    {
        return voltage/getTotalResistance();
    }

    /**
     * metohd pass through the circuit from the given resistor and update the current (and the voltage consequenly which flows through each resitor in circuit (recursive)
     * @param resistor the resistor which has the given current and from it we are calculating
     * @param current the current the the given resistor gets
     */
    //Todo another similer function that recieves end resistor so we won't calculate from the split to the end in vain
    private void setCircuitValues(Resistor resistor,double current)
    {
        if((resistor==null)||(resistor==endResistor)) //there is no need to contiue calculating
            return;
        if(resistor.resistor2==null&&resistor.resistor1!=null) //linear connection
        {
            //the next resistor gets all the current that the last resistor got
            resistor.resistor1.setCurrent(current);
            setCircuitValues(resistor.resistor1,current); //contiue from the next resisotr
        }

        else {
            Resistor cut = findCut(resistor.resistor1, resistor.resistor2); //where the 2 resistors splitting from 'resistor' meet again
            double resistance1=getResistanceOfPart(resistor.resistor1, cut); //the resistence of 1 side of splie
            double resistance2=getResistanceOfPart(resistor.resistor2, cut); //the resistence of other side of splie
            // current each side of split get is in reverse ratio to the resistences of each part
            double current1=current*(resistance2/(resistance1+resistance2));
            double current2=current*(resistance1/(resistance1+resistance2));
            resistor.resistor1.setCurrent(current1);
            resistor.resistor2.setCurrent(current2);
            // continue calculating for each side of split
            setCircuitValues(resistor.resistor1,current1);
            setCircuitValues(resistor.resistor2,current2);
            // after cut all the current is united again
            cut.setCurrent(current);
            //contiue calcuating from the cut resistor
            setCircuitValues(cut,current);
        }
    }

    /**
     * pass through the circuit from the start resistor to the end resitor and update the current (and the voltage consequenly) which flows through each resitor in circuit
     */
    public void setCircuitValues()
    {
        double totalCurrent = getTotalCurrent();
        startResistor.setCurrent(totalCurrent);
        startResistor.calculateVoltage();
        setCircuitValues(startResistor,totalCurrent);
    }
    //assumes a is connected to b
    @Override
    public String toString()
    {
        String res="";
        res+="Circuit:voltage="+voltage+"V";
        res+=System.lineSeparator();
        res+="Components:"+System.lineSeparator();
        for(Resistor resistor:resistors)
        {
            res+=resistor.toString();
            res+=System.lineSeparator();
        }
        res+="]";
        return res;
    }
}

package com.company;

import java.util.ArrayList;
import java.util.List;

public class Circuit
{
    private double voltage; //V
    private Resistor startResistor;
    private Resistor endResistor;
    private List<Resistor>resistors;


    public Circuit(double voltage,Resistor resistorPositive,Resistor resistorNegative,List<Resistor>resistors) {
        this.resistors=resistors;
        this.voltage = voltage;
        this.startResistor = resistorPositive;
        this.endResistor = resistorNegative;
        setCircuitValues();
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    private double getResistanceOfPart(Resistor from,Resistor to)
    {
        if(from==to)
            return 0;
        if(from.resistor2==null&&from.resistor1!=null) //linear
            return from.getResistance()+getResistanceOfPart(from.resistor1,to);
        Resistor cut = findCut(from.resistor1,from.resistor2);
        double resistance1 = getResistanceOfPart(from.resistor1,cut);
        double resistance2 = getResistanceOfPart(from.resistor2,cut);
        return from.getResistance()+resistance1*resistance2/(resistance1+resistance2)+getResistanceOfPart(cut,endResistor);
    }
    public double getTotalResistance()
    {
        return getResistanceOfPart(startResistor,endResistor);
    }
    public Resistor findCut(Resistor r1,Resistor r2)
    {
        List<Resistor>l1=resistorsGroupToList(r1);
        List<Resistor>l2=resistorsGroupToList(r2);
        for(int i=0;i<l1.size();i++)
        {
            for(int j=0;j<l2.size();j++)
            {
                if(l2.get(j)==l1.get(i)) //same resistor
                    return l2.get(j);
            }
        }
        return endResistor;
    }
    public List<Resistor>resistorsGroupToList(Resistor resistor)
    {
        List<Resistor>res = new ArrayList<>();
        addResistorsGroupToList(resistor,res);
        return res;
    }

    private void addResistorsGroupToList(Resistor resistor, List<Resistor> list) {
        if(resistor==null)
            return;
        if (resistor != endResistor) {
            list.add(resistor);
            addResistorsGroupToList(resistor.resistor1, list);
            addResistorsGroupToList(resistor.resistor2, list);
        }
    }
    public double getTotalCurrent()
    {
        return voltage/getTotalResistance();
    }
    private void setCircuitValues(Resistor resistor,double current)
    {
        if((resistor==null)||(resistor==endResistor))
            return;
        if(resistor.resistor2==null&&resistor.resistor1!=null) //linear
        {
            resistor.resistor1.setCurrent(current);
            resistor.resistor1.calculateVoltage();
            setCircuitValues(resistor.resistor1,current);
        }
        else {
            Resistor cut = findCut(resistor.resistor1, resistor.resistor2);
            double resistance1=getResistanceOfPart(resistor.resistor1, cut);
            double resistance2=getResistanceOfPart(resistor.resistor2, cut);
            double current1=current*(resistance2/(resistance1+resistance2));
            double current2=current*(resistance1/(resistance1+resistance2));
            resistor.resistor1.setCurrent(current1);
            resistor.resistor2.setCurrent(current2);
            resistor.resistor1.calculateVoltage();
            resistor.resistor2.calculateVoltage();
            setCircuitValues(resistor.resistor1,current1);
            setCircuitValues(resistor.resistor2,current2);
            cut.setCurrent(current);
            cut.calculateVoltage();
            setCircuitValues(cut,current);
        }
    }
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

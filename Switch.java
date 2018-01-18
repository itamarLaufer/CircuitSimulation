package com.company;

import java.util.List;

public class Switch extends Resistor {
    final double OPEN_RESISTANCE=999999999; //infintie
    //default switch is open
    public Switch(String name) {
        super(name,0);
        open();
    }
    public Switch(String name,boolean isOpen) {
        super(name,0);
        if(!isOpen)
            open();
    }

    public Switch(String name,boolean isOpen, Resistor resistor1,Resistor resistor2) {
        this(name,isOpen);
        this.resistor1=resistor1;
        this.resistor2=resistor2;
    }
    public void open()
    {
        this.setResistance(OPEN_RESISTANCE);
    }
    public void close()
    {
        this.setResistance(0);
    }
    public void setMode(boolean toOpen)
    {
        if(toOpen)
            open();
        else
            close();
    }

    @Override
    public String toString() {
        return super.toString()+" Switch{" +
                '}';
    }
}

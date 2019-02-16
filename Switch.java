package com.company;

/**
 * represents a switch which is a resistor that has 2 states open - > enourmous resistence and close -> 0 resistence
 */
public class Switch extends Resistor {
    final double OPEN_RESISTANCE=999999999; //infintie
    //default switch is open

    /**
     * creates an open switch
     * @param name the name of the resistor
     */
    public Switch(String name) {
        super(name,0);
        open();
    }

    /**
     * creates switch with the given data
     * @param name the name of the resistor
     * @param isOpen whether this resistor is open
     */
    public Switch(String name,boolean isOpen) {
        super(name,0);
        if(!isOpen)
            open();
    }

    /**
     * opens the switch
     */
    public void open()
    {
        this.setResistance(OPEN_RESISTANCE);
    }

    /**
     * closes the switch
     */
    public void close()
    {
        this.setResistance(0);
    }

    /**
     * change the mode (close/open) of the switch according to the parameter
     * @param toOpen whether this switch is open
     */
    public void setMode(boolean toOpen)
    {
        if(toOpen)
            open();
        else
            close();
    }
    /**
     * overrides setResistance because this kind of resisotr doesn't allow changing it's resistence (there are 2 modes only)
     * @param resistance
     */
    @Override
    public void setResistance(double resistance)
    {
        //do nothing
    }

    @Override
    public String toString() {
        return super.toString()+" Switch{" +
                '}';
    }
}

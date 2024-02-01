/**
 * This is the main class for the calculator. This class will focus on 
 * creating the calculator that shows on the screen. This calculator will
 * work with the calculator with GUI and calculator engine. 
 * 
 *
 * @author (Katherine Garcia) & Team Array
 * @version December 16th, 2023)
 */
public class Calculator
{
    // calls the gui from interface class
    private UserInterface gui;
    //calls the engine from calculations class
    private Calculations engine;
    
    /**
     * Shows the new calculator
     */
    public Calculator()
    {
        // initializes instance variables of engine and gui
        engine = new Calculations();
        gui = new UserInterface(engine);
    }

    /**
     * The method show() will make the calculator visible on the screen. 
     *
     * 
     * @return    the calculator will show if the boolean is true. 
     */
    public void show()
    {
        //the gui will show if it is true
        gui.setVisible(true);
    }
}

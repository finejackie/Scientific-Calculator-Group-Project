import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * This the user interface of the calculator. The user will be able to 
 * interact with the calculator. This class is responsible for putting up the 
 * calculator on the screen. It will refer to the calculations engine to 
 * complete the task.
 * 
 * @author: Jackie Fine & Team Array
 * @version 2023.12.16
 */ 
public class UserInterface
{  
    private Calculations calc;
    private boolean showingAuthor;
    private JFrame frame;
    private JTextField displayAnswer;
    private JLabel status;
    /**
     * Create a user interface.
     * @param engine The calculator engine.
     */
    public UserInterface(Calculations engine)
    {
        calc = engine;
        showingAuthor = true;
        makeFrame();
        frame.setVisible(true);
    }
    /**
     * Set the visibility of the interface.
     * @param visible true if the interface is to be made visible, false otherwise.
     */
    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }
    /**
     * Make the frame for the user interface.
     */
    private void makeFrame()
    {
        frame = new JFrame(calc.getTitle());
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(75, 75));
        contentPane.setBorder(new EmptyBorder( 50, 50, 50, 50));

        
        displayAnswer = new JTextField();
        contentPane.add(displayAnswer, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(new GridLayout(8, 5));
            addButton(buttonPanel, "+", () -> calc.plus());      
            addButton(buttonPanel, "-", () -> calc.minus());    
            addButton(buttonPanel, "*", () -> calc.multiply());   
            addButton(buttonPanel, "/'", () -> calc.divide());    
            addButton(buttonPanel, "C", () -> calc.clear());
            
            addButton(buttonPanel, "%", () -> calc.percent());  
            addButton(buttonPanel, "sin", () -> calc.sin());  
            addButton(buttonPanel, "cos", () -> calc.cos());   
            addButton(buttonPanel, "tan", () -> calc.tan ());  
            addButton(buttonPanel, "nCr", () -> calc.ncr());    
            
            addButton(buttonPanel, "n!", () -> calc.nabs());  
            addButton(buttonPanel, "cosec", () -> calc.cosec());   
            addButton(buttonPanel, "sec", () -> calc.sec());  
            addButton(buttonPanel, "cot", () -> calc.cot());  
            addButton(buttonPanel, "nPr", () -> calc.npr());   
            
            addButton(buttonPanel, "√", () -> calc.sqrt());   
            addButton(buttonPanel, "x^2", () -> calc.xsqrd());  
            addButton(buttonPanel, "x^3", () -> calc.xcube());  
            addButton(buttonPanel, "x^y", () -> calc.expon());   
            addButton(buttonPanel, "x^-1", () -> calc.minone());   
            
            addButton(buttonPanel, "e", () -> calc.e());   
            addButton(buttonPanel, "e^x", () -> calc.etox());   
            addButton(buttonPanel, "log", () -> calc.log());   
            addButton(buttonPanel, "ln", () -> calc.ln());   
            addButton(buttonPanel, "|x|", () -> calc.absolx());   
            
            addButton(buttonPanel, "π", () -> calc.pi());   
            addButton(buttonPanel, "nπ", () -> calc.npi());   
            addButton(buttonPanel, "2^x", () -> calc.twotox());               
            addNumberButton(buttonPanel, 0);
            addNumberButton(buttonPanel, 1);
            
            addNumberButton(buttonPanel, 2);
            addNumberButton(buttonPanel, 3);
            addNumberButton(buttonPanel, 4);
            addNumberButton(buttonPanel, 5);
            addNumberButton(buttonPanel, 6);
            
            addNumberButton(buttonPanel, 7);
            addNumberButton(buttonPanel, 8);
            addNumberButton(buttonPanel, 9);
            addButton(buttonPanel, "Negate", () -> calc.negate());
            addButton(buttonPanel, "=", () -> calc.calculateResult());
            
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel(calc.getAuthor());
        contentPane.add(status, BorderLayout.SOUTH);

        frame.pack();
    }
    /**
     * Add a button to the button panel.
     * @param panel The panel to receive the button.
     * @param buttonText The text for the button.
     * @param action Action to be taken by the button.
     */
    private void addButton(Container panel, String buttonText, ButtonAction action)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(e -> { action.act(); redisplay(); });
        panel.add(button);
    }
    /**
     * Add a number button to the button panel.
     * @param panel The panel to receive the button.
     * @param digit The single digit on the button.
     */
    private void addNumberButton(Container panel, int digit)
    {
        addButton(panel, "" + digit, () -> calc.numberPressed(digit));
    }
    /**
     * Update the interface display to show the current value of the 
     * calculator.
     */
    private void redisplay() {
        displayAnswer.setText("" + calc.getDisplayValue());
    }
    /**
     * Toggle the info display in the calculator's status area between the
     * author and version information.
     */
    private void showInfo()
    {
        if(showingAuthor)
            status.setText(calc.getVersion());
        else
            status.setText(calc.getAuthor());
        showingAuthor = !showingAuthor;
    }
    /**
     * Functional interface for button actions.
     */
    @FunctionalInterface
    private interface ButtonAction
    {
        /**
         * Act on a button press.
         */
        public void act();
    }
}

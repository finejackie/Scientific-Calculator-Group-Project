/**
 * This is the calculations part of the calculator. This class will focus on 
 * calculating all the functions and operations. 
 *
 * @author (Saransh Ketulkumar Shah ) & Team Array
 * @version December 16th, 2023)
 */

public class Calculations  {
    private boolean buildingDisplayValue;
    private boolean haveLeftOperand;
    private char lastOperator;
    private double displayValue;
    private double leftOperand;
    private boolean inDegrees;
    public Calculations() {
        clear(); //this opens the calculator with an empty screen
    }
    public double getDisplayValue() {
        return displayValue; //this displays the value of the button pressed
    }
    public void numberPressed(int number) { //displays the number pressed
        if (buildingDisplayValue) {
            displayValue = displayValue * 10 + number;
        } else {
            displayValue = number;
            buildingDisplayValue = true;
        }
    }
    public void plus() { //adds numbers
        applyOperator('+');
    }
    public void minus() { //subtracts numbers
        applyOperator('-');
    }
    public void multiply() { //multiplies numbers
         applyOperator('*');
    }
    public void divide() { //divides numbers
        applyOperator('/');
    }
    public void percent() { //shows number in percents
        if (buildingDisplayValue) {
            displayValue = leftOperand * displayValue / 100.0;
        }
    }
    public void sin() { //takes the sin of a number
        displayValue = inDegrees ? Math.sin(Math.toRadians(displayValue)) : Math.sin(displayValue);
    }
    public void cos() { // takes the cos of a number
       displayValue = inDegrees ? Math.cos(Math.toRadians(displayValue)) : Math.cos(displayValue);
    }
    public void tan() { //takes the tan of a number
      displayValue = inDegrees ? Math.tan(Math.toRadians(displayValue)) : Math.tan(displayValue);
    }
    public void cosec() { //takes the cosec of a number
        double sinValue = sinValue(displayValue);
        if (sinValue != 0) {
            displayValue = 1 / sinValue;
        } else {
            keySequenceError(); //shows error if invalid number
        }
    }
    public void sec() { //takes the sec of a number
        double cosValue = cosValue(displayValue);
        if (cosValue != 0) {
            displayValue = 1 / cosValue;
        } else {
            keySequenceError();
        }
    }
    public void cot() { //takes the cot of a number
        double tanValue = tanValue(displayValue);
        if (tanValue != 0) {
            displayValue = 1 / tanValue;
        } else {
            keySequenceError();
        }
    }
    public void nabs() { //takes into account factorials
        if (buildingDisplayValue) {
            int n = (int) displayValue;
            if (n >= 0) {
                displayValue = factorial(n);
            } else {
                keySequenceError();
            }
        }
    }
    public void npr() { //takes into account factorials
        if (buildingDisplayValue) {
            int n = (int) displayValue;
            int r = (int) leftOperand;
            if (n >= r && n >= 0 && r >= 0) {
                displayValue = factorial(n) / factorial(n - r);
            } else {
                keySequenceError();
            }
        }
    }
    public void ncr() { //takes into account factorials
        if (buildingDisplayValue) {
            int n = (int) displayValue;
            int r = (int) leftOperand;
            if (n >= r && n >= 0 && r >= 0) {
                displayValue = factorial(n) / (factorial(r) * factorial(n - r));
            } else {
                keySequenceError();
            }
        }
    }
    public void sqrt() { //takes the square root of a number
        if (buildingDisplayValue) {
            if (displayValue >= 0) {
                displayValue = Math.sqrt(displayValue);
            } else {
                keySequenceError();
            }
        }
    }
    public void xsqrd() { //takes the number squared
          displayValue = Math.pow(displayValue, 2);
    }
    public void xcube() { //takes the number cubed
         displayValue = Math.pow(displayValue, 3);
    }
    public void expon() { //takes the exponent of a number
        displayValue = Math.pow(leftOperand, displayValue);
        haveLeftOperand = true;
        leftOperand = displayValue;
    }
    public void minone() { //takes the x minus 1 of a number
        if (displayValue != 0) {
            displayValue = 1 / displayValue;
        } else {
            keySequenceError();
        }
    }
    public void e() { //takes the natural e of a number
          displayValue = Math.E;
    }
    public void etox() { //takes the e raised to x of a number
           displayValue = Math.exp(displayValue);
    }
    public void log() { //takes the log of a number
        if (displayValue > 0) {
            displayValue = Math.log10(displayValue);
        } else {
            keySequenceError();
        }
    }
    public void ln() { //takes the natural log of a number
        if (displayValue > 0) {
            displayValue = Math.log(displayValue);
        } else {
            keySequenceError();
        }
    }
    public void absolx() { // takes the absolute value of a number
           displayValue = Math.abs(displayValue);
    }
    public void pi() { //calculates pi
       displayValue = Math.PI;
    }
     public void npi() { //calculate the number times pi
        if (buildingDisplayValue) {
            displayValue *= Math.PI;
        }
    }
    public void twotox() { //calculates 2 to the x
        if (buildingDisplayValue) {
            displayValue = Math.pow(2, displayValue);
        }
    }
    public void tentox() { // calculate 10 to the x
        if (buildingDisplayValue) {
            displayValue = Math.pow(10, displayValue);
        }
    }
    public boolean isInDegrees() { 
          return inDegrees;
    }
    public void calculateResult() { //calculates the results
        if (!haveLeftOperand || lastOperator == '?') {
            return; // Do nothing if there's no operation to perform
        }
        switch (lastOperator) {
            case '+':
                displayValue = leftOperand + displayValue;
                break;
            case '-':
                displayValue = leftOperand - displayValue;
                break;
            case '*':
                displayValue = leftOperand * displayValue;
                break;
            case '/':
                if (displayValue != 0) {
                    displayValue = leftOperand / displayValue;
                } else {
                    keySequenceError();
                }
                break;
            // ... other cases as necessary ...
        }
        leftOperand = 0;
        haveLeftOperand = false;
        lastOperator = '?';
    }
     public void negate() { //turns the number to a negative
          displayValue = -displayValue;
    }
    private void applyOperator(char operator) {
        if (!buildingDisplayValue && !(haveLeftOperand && lastOperator == '?')) {
            keySequenceError();
            return;
        }

        if (lastOperator != '?') {
            calculateResult();
        } else {
            haveLeftOperand = true;
            leftOperand = displayValue;
        }
        lastOperator = operator;
        buildingDisplayValue = false;
    }
    private void keySequenceError() {
        System.out.println("A key sequence error has occurred.");
        clear();
    }
    public void clear() { //when C is pressed, it clears the screen
        lastOperator = '?';
        haveLeftOperand = false;
        buildingDisplayValue = false;
        displayValue = 0;
    }
    public String getTitle() { //displays title of the calculator
      return "Java Scientific Calculator";
    }
    public String getAuthor() { //displays the author of the calculator
      return "Group Array";  // Our team name (Jackie’s idea)
    }
    public String getVersion() { //displays the version of the calculator
       return "Version 1.0";
    }
    public void setDegrees(boolean inDegrees) {
        this.inDegrees = inDegrees;
    }
    private double sinValue(double value) {
        return inDegrees ? Math.sin(Math.toRadians(value)) : Math.sin(value);
    }
    private double cosValue(double value) {
        return inDegrees ? Math.cos(Math.toRadians(value)) : Math.cos(value);
    }
    private double tanValue(double value) {
        return inDegrees ? Math.tan(Math.toRadians(value)) : Math.tan(value);
    }
    private int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

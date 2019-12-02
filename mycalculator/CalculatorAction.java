/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycalculator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Gil
 */
public class CalculatorAction implements ActionListener{
    
    private double lastNumEntered;
    private boolean doReset;
    private String selectedAction;
    private JButton selectedButton; 
    private final JLabel lblResult;
    
    public CalculatorAction(JLabel result){
        this.lblResult = result;
        this.lastNumEntered = 0;
        this.selectedAction = "";
        this.doReset = false;
        this.selectedButton = null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String btnText = ((JButton)e.getSource()).getText();
        String lblText;

        if(doReset){
            reset();
            doReset = false;
        }

        lblText = lblResult.getText();

        if(btnText.length() == 1 && Character.isDigit(btnText.charAt(0))){
            lblResult.setText(((isZero(lblText) && (!hasDot(lblText))) ? "" : lblText) + btnText);
        } else{
            switch(btnText){
                case "+/-": 
                    if(lblText.startsWith("-"))
                        lblResult.setText(lblText.substring(1));
                    else if(!isZero(lblText))
                        lblResult.setText("-" + lblText);
                    break;
                case ".": 
                    if(!hasDot(lblText))
                        lblResult.setText(lblText + ".");
                    break;
                case "AC": 
                    reset();
                    break;
                case "+": case "-": case "*": case "/":
                    if(!selectedAction.isEmpty()){
                        try{
                            lastNumEntered = calculateResult(selectedAction);
                        } catch(ArithmeticException ex){
                            lblResult.setText("ERROR!");
                            doReset = true;
                            break;
                        }
                    }else{
                        lastNumEntered = Double.parseDouble(lblText);
                    }

                    if(selectedButton != null)
                        selectedButton.setBackground(new JButton().getBackground());

                    selectedButton = (JButton)e.getSource();
                    selectedButton.setBackground(Color.LIGHT_GRAY);
                    selectedAction = btnText;
                    lblResult.setText("0"); 
                    break;
                case "=":
                    if(selectedAction.isEmpty())
                        break;
                    try{
                        double result = calculateResult(selectedAction);
                        lblResult.setText((result % 1 == 0)? Integer.toString((int)result) : Double.toString(result));
                        lblResult.setBackground(Color.LIGHT_GRAY);
                    } catch(ArithmeticException ex){
                        lblResult.setText("ERROR!");
                    } finally{
                        doReset = true;
                        if(selectedButton != null)
                            selectedButton.setBackground(new JButton().getBackground());
                    }
                    break;
            }
        }
    }
    
    private double calculateResult(String action) throws ArithmeticException{
        double currentNum = Double.parseDouble(lblResult.getText());
        
        switch(action){
            case "+": return lastNumEntered + currentNum;
            case "-": return lastNumEntered - currentNum;
            case "*": return lastNumEntered * currentNum;
            case "/": 
                if(currentNum != 0)
                    return lastNumEntered / currentNum;
                else
                    throw new ArithmeticException();
            default: return 0;
        }
    }
    
    private void reset(){
        lastNumEntered = 0;
        lblResult.setText("0");
        lblResult.setBackground(Color.WHITE);
        selectedAction = "";
        if(selectedButton != null){
            selectedButton.setBackground(new JButton().getBackground());
            selectedButton = null;
        }
    }
    
    private boolean isZero(String num){
        return (Double.parseDouble(num) == 0);
    }
    
    private boolean hasDot(String num){
        return (num.indexOf('.') >= 0);
    }
}

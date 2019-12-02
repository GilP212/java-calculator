package mycalculator;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Gil
 */
public class CalculatorFrame extends JFrame{
    
    public static final int FRAME_SIZE_H = 700, FRAME_SIZE_V = 500, RESULT_FONT_SIZE = 50;
    
    private final JLabel lblResult;
    private final ButtonGridPanel btnPanel;
    
    public CalculatorFrame(){
        super("Calculator");
        
        
        lblResult = new JLabel("0");
        
        btnPanel = new ButtonGridPanel();
        
        this.setVisible(true);
    }
    
    public void initButtons(){
        add(btnPanel, BorderLayout.CENTER);
    }
    
    public void initLabel(){
        lblResult.setHorizontalAlignment(JLabel.RIGHT);
        lblResult.setBackground(Color.WHITE);
        lblResult.setOpaque(true);
        lblResult.setFont(new Font("Serif", Font.PLAIN, RESULT_FONT_SIZE));
        add(lblResult, BorderLayout.NORTH);
    }
    
    public void initFrame(){
        setLayout(new BorderLayout());
        setSize(FRAME_SIZE_H, FRAME_SIZE_V);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    
    private class ButtonGridPanel extends JPanel{
        public static final int NUM_ROWS = 3, NUM_COLS = 6, GAP = 10, BTN_FONT_SIZE = 50;
        
        private JButton[] btnArray = new JButton[NUM_ROWS * NUM_COLS];
        
        public ButtonGridPanel(){
            this.setLayout(new GridLayout(NUM_ROWS, NUM_COLS, GAP, GAP));
            
            Font btnFont = new Font("Serif", Font.PLAIN, BTN_FONT_SIZE);
            CalculatorAction btnListener = new CalculatorAction(lblResult);
            
            for(int i = 0; i < NUM_ROWS * NUM_COLS; i++){
                String btnText;
                switch(i){
                    case 0: btnText = "7"; break;
                    case 1: btnText = "8"; break;
                    case 2: btnText = "9"; break;
                    case 3: btnText = "/"; break;
                    case 4: btnText = "="; break;
                    case 5: btnText = "AC"; break;
                    case 6: btnText = "4"; break;
                    case 7: btnText = "5"; break;
                    case 8: btnText = "6"; break;
                    case 9: btnText = "+"; break;
                    case 10: btnText = "-"; break;
                    case 11: btnText = "*"; break;
                    case 12: btnText = "1"; break;
                    case 13: btnText = "2"; break;
                    case 14: btnText = "3"; break;
                    case 15: btnText = "0"; break;
                    case 16: btnText = "+/-"; break;
                    case 17: btnText = "."; break;
                    default: btnText = ""; break;
                }
                btnArray[i] = new JButton(btnText);
                btnArray[i].setFont(btnFont);
                btnArray[i].addActionListener(btnListener);
                this.add(btnArray[i]);
            }
        }
    }
    
    public static void main(String[] args) {
        CalculatorFrame window = new CalculatorFrame();
        
        window.initFrame();
        
        window.initLabel();
        
        window.initButtons();
    }
    
}

package Words.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jun on 12/8/2014.
 */
public class AddSwapButtonListner implements ActionListener {
    JButton button;
    Color originColor;
    public AddSwapButtonListner(JButton button){
        this.button = button;
        originColor = button.getBackground();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(SwapAddListener.flip()){
            button.setText("Stopping Adding");
            button.setBackground(Color.red);
        }else {
            button.setText("Add Swap");
            button.setBackground(originColor);
        }
    }
}

package Words.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jun on 12/8/2014.
 */
public class AddSwapButtonListner implements ActionListener {
    JButton button;

    public AddSwapButtonListner(JButton button){this.button = button;}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(SwapAddListener.flip()){
            button.setText("Stopping Adding");
        }else {
            button.setText("Add Swap");
        }
    }
}

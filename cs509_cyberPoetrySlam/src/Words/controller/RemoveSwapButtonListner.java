package Words.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jun on 12/8/2014.
 */
public class RemoveSwapButtonListner implements ActionListener {
    JButton button;
    public RemoveSwapButtonListner(JButton button) {this.button = button;}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(SwapRemoveListener.flip()){
            button.setText("Remove Swap");
        }else {
            button.setText("Stop Removing");
        }
    }
}

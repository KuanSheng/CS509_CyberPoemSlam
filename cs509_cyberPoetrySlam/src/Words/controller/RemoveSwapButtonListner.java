package Words.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jun on 12/8/2014.
 */
public class RemoveSwapButtonListner implements ActionListener {
    JButton button;
    Color originColor = null;
    public RemoveSwapButtonListner(JButton button) {
        this.button = button;
        originColor = button.getBackground();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(SwapRemoveListener.flip()){
            System.out.println("color" + button.getBackground());
            button.setText("Stop Removing");
            button.setBackground(Color.red);
        }else {
            button.setText("Remove Swap");
            button.setBackground(originColor);
//            button.setBackground(Co);
        }
    }
}

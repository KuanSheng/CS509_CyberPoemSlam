package Words.controller;

import Words.model.Board;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by Jun on 12/8/2014.
 */


public class SwapAddListener implements ListSelectionListener {
    private JTable jtable;
    private Board board;
    private static boolean active = true;

    public SwapAddListener(JTable jtable, Board board) {
        this.jtable = jtable;
        this.board = board;
    }

    public static void setActive(){active = true;}
    public static void setInactive(){active = false;}

    //        jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent event) {
        if(!active || event.getValueIsAdjusting() ) return; // this listener would be called twice
        //only act the second time -- when releasning
        if (jtable.getSelectedRow() > -1) {
            // print first column value from selected row
            System.out.println("add words to swap : valueChanged.SwapAddListner");
            System.out.println(jtable.getValueAt(jtable.getSelectedRow(), 0).toString());
            System.out.println(jtable.getValueAt(jtable.getSelectedRow(), 1).toString());
            board.addOurSwap(jtable.getSelectedRow());

        }
    }

}



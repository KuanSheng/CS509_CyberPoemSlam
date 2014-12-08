package Words.controller;

import Words.model.Board;
import Words.view.Application;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by Jun on 12/8/2014.
 */
public class SwapRemoveListener implements ListSelectionListener {
    private JTable jtable;
    private static boolean active = false;
    private Board board;
    private Application app;

    public SwapRemoveListener(JTable jtable, Board board, Application app) {
        this.jtable = jtable;
        this.board = board;
        this.app = app;
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
            System.out.println("romoved words to swap : valueChanged.SwapRemoveListner");
            System.out.println(jtable.getValueAt(jtable.getSelectedRow(), 0).toString());
            System.out.println(jtable.getValueAt(jtable.getSelectedRow(), 1).toString());

            board.removeOurSwap(jtable.getSelectedRow());
            jtable.repaint();
            app.refreshTables();
        }
    }

    public boolean respond(){
        jtable.repaint();
        return true;
    }

    public static boolean isActive() {return active;}

    public static boolean flip(){ return active = !active;}
}

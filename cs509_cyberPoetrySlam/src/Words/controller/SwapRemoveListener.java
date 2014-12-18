package Words.controller;

import Words.model.Board;
import Words.model.OurSwap;
import Words.view.Application;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/** this controller removes a swap pair
 * Created by Jun on 12/8/2014.
 */
public class SwapRemoveListener implements ListSelectionListener {
    private JTable jtable;
    private static boolean active = false;
    private Board board;
    private Application app;
    private OurSwap swap;

//    public SwapRemoveListener(JTable jtable, Board board, Application app) {
//        this.jtable = jtable;
//        this.board = board;
//        this.app = app;
//    }

    public SwapRemoveListener(JTable jtable, OurSwap swap, Application app) {
        this.jtable = jtable;
        this.swap = swap;
        this.app = app;
    }

    /**
     * set controller active --  clicking in offer table (third one from left) would delete a swap pair
     */
    public static void setActive(){active = true;}

    /**
     * set controller inactive --  clicking in offer table (third one from left) would NOT delete a swap pair
     */
    public static void setInactive(){active = false;}

    /**
     * table clicked, check if a swap pair should be removed, and do it if yes
     * @param event
     */
    @Override
    public void valueChanged(ListSelectionEvent event) {
        if(!active || event.getValueIsAdjusting() ) return; // this listener would be called twice
        //only act the second time -- when releasning
        if (jtable.getSelectedRow() > -1) {
            // print first column value from selected row
            System.out.println("romoved words to swap : valueChanged.SwapRemoveListner");
            System.out.println(jtable.getValueAt(jtable.getSelectedRow(), 0).toString());
            System.out.println(jtable.getValueAt(jtable.getSelectedRow(), 1).toString());

            swap.removeOffer(jtable.getSelectedRow());
//            board.removeOurSwap(jtable.getSelectedRow());
//            jtable.repaint();
            jtable.clearSelection();
            app.refreshTables();
        }
    }


    /**
     * returns status of the controller
     * @return
     */
    public static boolean isActive() {return active;}

    /**
     * reverse the status of the controller
     * @return
     */
    public static boolean flip(){ return active = !active;}
}
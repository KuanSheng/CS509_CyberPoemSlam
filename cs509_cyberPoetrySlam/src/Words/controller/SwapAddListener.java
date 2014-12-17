package Words.controller;

import Words.model.Board;
import Words.model.OurSwap;
import Words.view.Application;
import broker.util.Swap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Add swap pairs
 * Created by Jun on 12/8/2014.
 */


public class SwapAddListener implements ListSelectionListener {
    public static enum  TYPE  {WordTable, WordTypeTable};
    private JTable jtable;
    private Board board;
    private static boolean active = false;
    private Application app;
    private OurSwap swap;
    private TYPE tableType;


    public SwapAddListener(JTable jtable, OurSwap swap, Application app, TYPE tableType){
        this.jtable = jtable;
        this.app = app;
        this.swap = swap;
        this.tableType = tableType;
        this.board = app.getModel().getBoard();
    }

    /**
     * set controller active, so that clicking in tables would add a new pair
     */
    public static void setActive(){active = true;}

    /**
     * set controller inactive, so clicking tables would NOT add new pairs
     */
    public static void setInactive(){active = false;}


    /**
     * add offered word by the table that is clicked
     * @param event the selection event
     */
    @Override
    public void valueChanged(ListSelectionEvent event) {
        if(!active || !event.getValueIsAdjusting() ) return; // this listener would be called twice
        switch (tableType){
            case WordTable:
                addByWordTable();
                break;
            case WordTypeTable:
                addByWordTypeTable();
                break;

        }

    }

    /**
     * add new swap pair by selecting in word table
     */
    void addByWordTable(){
        //only act the second time -- when releasning
        if (jtable.getSelectedRow() > -1) {
            // print first column value from selected row
            System.out.println("add words to swap : valueChanged.SwapAddListner");
            System.out.println(jtable.getValueAt(jtable.getSelectedRow(), 0).toString());
            System.out.println(jtable.getValueAt(jtable.getSelectedRow(), 1).toString());
//            board.addOurSwap(jtable.getSelectedRow());
            swap.addOffer(jtable.getSelectedRow());
//            jtable.repaint();
            app.refreshTables();
            jtable.clearSelection();
        }
    }

    /**
     * add new swap pair by selecting in word type table
     */
    void addByWordTypeTable(){
        int indexSelected = jtable.getSelectedRow();
        if(indexSelected < 0) return;
        if(jtable.getValueAt(jtable.getSelectedRow(),0).equals(0)) return;//if there's no words of that type, just return
        new AddRandomWordForSwap(board, swap, app).addRandomWord(
                (String)jtable.getValueAt(jtable.getSelectedRow(),0)
        );
        jtable.clearSelection();
    }


    /**
     * return the status of the controller
     * @return true if active (clicing tables would add new pairs)
     */
    public static boolean isActive() {return active;}

    /**
     * change ths status of the controller
     * @return the status of the controller after this execution
     */
    public static boolean flip(){ return active = !active;}
}



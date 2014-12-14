package Words.controller;

import Words.model.Board;
import Words.model.Word;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by Jun on 12/14/2014.
 */
public class PopupWordController implements ListSelectionListener {

    JTable jtable;
    Board board;
    public PopupWordController(JTable jtable, Board board){
        this.jtable = jtable;
        this.board = board;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //pop up only when SwapAddListner is not adding word for swap
        if(!SwapAddListener.isActive()){
            if (jtable.getSelectedRow() > -1) {
                Word popWord = board.getunprotectedWords().get(jtable.getSelectedRow());

            }
        }
    }
}

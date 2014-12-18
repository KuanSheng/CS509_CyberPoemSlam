package Words.controller;

import Words.model.Board;
import Words.model.Word;
import Words.view.Application;
import Words.view.WordTable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This controller pops up an unprotected word, when it's clicked in the word table (the left most one in our gui)
 * Created by Jun on 12/14/2014.
 */
public class PopupWordController implements ListSelectionListener {

    WordTable table;
    Board board;
    Application app;

    public PopupWordController(WordTable table, Board board, Application app){
        this.table = table;
        this.board = board;
        this.app = app;
    }

    /**
     * pop up the a word by the index of row selected in the jtable
     * @param e selection event
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        //pop up only when SwapAddListner is not adding word for swap
        if(!SwapAddListener.isActive()){
            if (table.getJtable().getSelectedRow() > -1) {
                Word popWord = board.getunprotectedWords().get(table.getJtable().getSelectedRow());
                app.getModel().setHighlightWord(popWord);
                app.getPanel().repaint();
            }
        }
    }
}
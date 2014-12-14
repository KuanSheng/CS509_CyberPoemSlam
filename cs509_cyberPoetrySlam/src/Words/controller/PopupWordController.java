package Words.controller;

import Words.model.Board;
import Words.model.Word;
import Words.view.Application;
import Words.view.WordTable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
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

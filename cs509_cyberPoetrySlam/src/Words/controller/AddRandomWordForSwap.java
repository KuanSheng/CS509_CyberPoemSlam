package Words.controller;

import Words.model.Board;
import Words.model.OurSwap;
import Words.model.Word;
import Words.model.WordModel;
import Words.view.Application;
import broker.util.Swap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This controller adds a "random" word of some type to be offered for swap
 * Created by Jun on 12/11/2014.
 */
public class AddRandomWordForSwap implements ActionListener{
    private Board board;
    private Application app;
    OurSwap swap;

    /**
     * constructor
     * @param board the board
     * @param swap the swap model
     * @param app the GUI
     */
    public AddRandomWordForSwap(Board board,OurSwap swap, Application app){
        this.board = board;
        this.app = app;
        this.swap = swap;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        addRandomWord(command);
    }

    /**
     * add rundom word of type "type" into the offered words
     * @param type the type of word to be offered
     * @return true if one word of type found, false otherwise
     */
    boolean addRandomWord(String type){
        for(Word w : board.getunprotectedWords()){
            if(w.getWordType() == Word.getTypeInt(type)){
                if(swap.getOurOffer().size() == swap.SWAP_LIMIT) swap.removeOffer(0);
                swap.addOffer(w);
                app.refreshTables();
                return true;
            }
        }
        return false;
    }


}
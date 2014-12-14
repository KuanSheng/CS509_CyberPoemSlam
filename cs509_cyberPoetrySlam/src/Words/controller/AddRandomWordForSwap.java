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
 * Created by Jun on 12/11/2014.
 */
public class AddRandomWordForSwap implements ActionListener{
    private Board board;
    private Application app;
    OurSwap swap;
    private static String[] wordTypeDefinition = {"verb", "adj", "noun", "adv"};
    public static final String ADD_VERB = "ADD_VERB";
    public static final String ADD_ADJ = "ADD_ADJ";
    public static final String ADD_NOUN = "ADD_NOUN";
    public static final String ADD_ADV = "ADD_ADV";

    public AddRandomWordForSwap(Board board,OurSwap swap, Application app){
        this.board = board;
        this.app = app;
        this.swap = swap;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if()
        String command = e.getActionCommand();
        if(command.equals(ADD_VERB)) addRandomVerb();
        else if(command.equals(ADD_ADJ)) addRandomAdj();
        else if(command.equals(ADD_NOUN)) addRandomNoun();
        else if(command.equals(ADD_ADV)) addRandomAdv();
    }

    //    public enum
    public boolean addRandom(int type){
        switch (type){
            case Word.ADJ_INT :
                return addRandomAdj();
            case Word.ADV_INT :
                return addRandomAdv();
            case Word.NOUN_INT :
                return addRandomNoun();
            case Word.VERB_INT :
                return addRandomVerb();
            default:
                return false;
        }
    }

    private boolean addRandomVerb(){
        for(Word w : board.getunprotectedWords()){
            if(w.getWordType() == Word.VERB_INT){
                    if(swap.getOurOffer().size() == swap.SWAP_LIMIT) swap.removeOffer(0);
                    swap.addOffer(w);
//                    swap.addRequest();
                    app.refreshTables();
                    return true;
            }
        }

        return false;
    }

    private boolean addRandomAdj(){
        for(Word w : board.getunprotectedWords()){
            if(w.getWordType() == Word.ADJ_INT){
                if(swap.getOurOffer().size() == swap.SWAP_LIMIT) swap.removeOffer(0);
                swap.addOffer(w);
//                swap.addRequest();
                app.refreshTables();
                return true;
            }
        }
        return false;
    }

    private boolean addRandomNoun(){
        for(Word w : board.getunprotectedWords()){
            if(w.getWordType() == Word.NOUN_INT){
                if(swap.getOurOffer().size() == swap.SWAP_LIMIT) swap.removeOffer(0);
                swap.addOffer(w);
//                swap.addRequest();
                app.refreshTables();
                return true;
            }
        }
        return false;
    }

    private boolean addRandomAdv() {
        for (Word w : board.getunprotectedWords()) {
            if (w.getWordType() == Word.ADV_INT) {
                if(swap.getOurOffer().size() == swap.SWAP_LIMIT) swap.removeOffer(0);
                swap.addOffer(w);
//                swap.addRequest();
                app.refreshTables();
                return true;
            }
        }
        return false;
    }
}

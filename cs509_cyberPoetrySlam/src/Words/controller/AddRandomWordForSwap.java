package Words.controller;

import Words.model.Board;
import Words.model.Word;
import Words.model.WordModel;
import Words.view.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jun on 12/11/2014.
 */
public class AddRandomWordForSwap implements ActionListener{
    private Board board;
    private Application app;
    private static String[] wordTypeDefinition = {"verb", "adj", "noun", "adv"};
    public static final String ADD_VERB = "ADD_VERB";
    public static final String ADD_ADJ = "ADD_ADJ";
    public static final String ADD_NOUN = "ADD_NOUN";
    public static final String ADD_ADV = "ADD_ADV";

    public AddRandomWordForSwap(Board board, Application app){
        this.board = board;
        this.app = app;
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
            case 1 :
                return addRandomNoun();
            case 2 :
                return addRandomAdj();
            case 3 :
                return addRandomAdv();
            default:
                return false;
        }
    }

    private boolean addRandomVerb(){
        for(Word w : board.getunprotectedWords()){
            if(w.getWordType() == WordModel.VERB_INT){
                if(board.addOurSwap(w)){
                    app.refreshTables();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean addRandomAdj(){
        for(Word w : board.getunprotectedWords()){
            if(w.getWordType() == WordModel.ADJ_INT){
                if(board.addOurSwap(w)){
                    app.refreshTables();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean addRandomNoun(){
        for(Word w : board.getunprotectedWords()){
            if(w.getWordType() == WordModel.NOUN_INT){
                if(board.addOurSwap(w)){
                    app.refreshTables();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean addRandomAdv(){
        for(Word w : board.getunprotectedWords()){
            if(w.getWordType() == WordModel.ADJ_INT){
                if(board.addOurSwap(w)){
                    app.refreshTables();
                    return true;
                }
            }
        }
        return false;
    }
}

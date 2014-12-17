package Words.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jun on 12/11/2014.
 */
public class OurSwap implements Serializable{
    public static final int SWAP_LIMIT = 5; //limit the # of words to swap one time to 5;
    private Board board;
    private ArrayList<Word> ourOffer;
    private ArrayList<WordSignature> ourRequest;
    private ArrayList<Word> theirRequest;
    boolean success;

    public OurSwap(Board b){
        board = b;
        ourOffer = new ArrayList<Word>();
        ourRequest = new ArrayList<WordSignature>();
//        ourRequest = new Object[][];
        theirRequest = new ArrayList<Word>();
        success = true;
    }

    /**
     * this function should be called everytime request changes, so that previous failure would be washed away
     * @return
     */
    private boolean statusSucess(){
        return success = true;
    }

    /**
     * add a request word for this swap
     * @param value the word you want, * means any
     * @param type the type of word you want, * means any
     * @return always true.
     */
    public boolean addRequest(String value, String type){
        if(value == null || type == null) return false;
        ourRequest.add(new WordSignature(value, type));
        return true;
    }

    /**
     * default addRequest, called when just want a new word and don't care about its value or type
     * @return
     */
    public boolean addRequest(){
        return this.addRequest("*", "*");
    }

    /**
     * add a word to swap out
     * @param word the word to add
     * @return true if success, false if word not in unprotected area.
     */
    public boolean addOffer(Word word){
        if(word == null) return false;
        int index = board.getunprotectedWords().indexOf(word);
        if(index >= 0){
            if(ourOffer.size() == SWAP_LIMIT){ //remove the first one to make room for a new word if already reached limit
                removeOffer(0);
            }
            ourOffer.add(board.getunprotectedWords().remove(index));
            ourRequest.add(new WordSignature("*","*"));
            statusSucess();
            return true;
        }
        return false;
    }

    /**
     * add the unproteced word at index in the arraylist of Board into the swap
     * @param index
     * @return
     */
    public boolean addOffer(int index){
        if(index < 0 || index > board.getunprotectedWords().size()) return false;

        return this.addOffer(board.getunprotectedWords().get(index));

//        if(w == null)
    }

    public boolean removeOffer(int selectedRow) {
        if(selectedRow < 0 || selectedRow > ourOffer.size()) return false;
        ourRequest.remove(selectedRow);
        board.getunprotectedWords().add(ourOffer.remove(selectedRow));
        statusSucess();
        return true;
    }

    public ArrayList<Word> getOurOffer() {
        return ourOffer;
    }

    public ArrayList<WordSignature> getOurRequest() {
        return ourRequest;
    }

    public ArrayList<Word> getTheirRequest() {
        return theirRequest;
    }

    public boolean clear() {
        ourOffer.clear();
        ourRequest.clear();
        statusSucess();
        return true;
    }

    public boolean getStatus(){
        return success;
    }

    public boolean setFailure(){
        success = false;
        return true;
    }
}

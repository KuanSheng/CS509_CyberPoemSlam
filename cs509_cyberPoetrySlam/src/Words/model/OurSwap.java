package Words.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jun on 12/11/2014.
 */
public class OurSwap implements Serializable{
    private Board board;
    private ArrayList<Word> ourOffer;
    private ArrayList<WordSignature> ourRequest;
//    private Object[][] ourRequest;
    private ArrayList<Word> theirRequest;

    public OurSwap(Board b){
        board = b;
        ourOffer = new ArrayList<Word>();
        ourRequest = new ArrayList<WordSignature>();
//        ourRequest = new Object[][];
        theirRequest = new ArrayList<Word>();
    }

    public boolean addRequest(String value, String type){
        if(value == null || type == null) return false;
        ourRequest.add(new WordSignature(value, type));
        return true;
    }

    public boolean addRequest(){
        return this.addRequest("*", "*");
    }

    public boolean addOffer(Word word){
        if(word == null) return false;
        int index = board.getunprotectedWords().indexOf(word);
        if(index >= 0){
            ourOffer.add(board.getunprotectedWords().remove(index));
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
        ourRequest.add(new WordSignature("*","*"));
        return this.addOffer(board.getunprotectedWords().get(index));

//        if(w == null)
    }

    public boolean removeOffer(int selectedRow) {
        if(selectedRow < 0 || selectedRow > ourOffer.size()) return false;
        ourRequest.remove(selectedRow);
        board.getunprotectedWords().add(ourOffer.remove(selectedRow));
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
        return true;
    }
}

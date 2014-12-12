package Words.model;

import java.util.ArrayList;

/**
 * Created by Jun on 12/11/2014.
 */
public class OurSwap {
    private Board board;

    private ArrayList<Word> offer;
    private ArrayList<Swap> request;




    private class Swap{
        String value;
        String type;
        public Swap(String value, String type){
            this.value = value;
            this.type = type;
        }
    }
}

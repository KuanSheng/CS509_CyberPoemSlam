package Words.model;

import com.sun.rowset.internal.Row;

import java.util.ArrayList;

public class Poem extends Element {
    ArrayList<Row> rowList = new ArrayList<Row>();
    //inherited x and y from Element
//    int x;
//    int y;
    int x_last;
    int y_last;

    public Poem(int x, int y) {
        super(2, x, y);
//        this.x = x;
//        this.y = y;
//        words = null;
    }

    public Poem(int x, int y, ArrayList<Row> r){
        super(2, x, y);
        rowList = r;
    }

    //move the pome to a new position at (x , y)
    public boolean move(int x, int y){
        this.x = x;
        this.y = y;
        return true;
    }

    public boolean stabilize(int x, int y){
        x_last = x;
        y_last = y;
        return true;
    }

    public void addFirstRow(Row r){
        rowList.add(0, r);
    }

    public void addLastRow(Row r) {
        rowList.add(r);
    }

    public Element select(int x, int y){

        return this; //TODO to be modified
    }

    public Element select(int top, int left, int bottom, int right){
        return this; //TODO to be modified
    }

    public ArrayList<Word> releaseMyself(){
        return new ArrayList<Word>(); // todo to be modified
    }
//    public void addWord(Word word) {
//        words.add(word);
//    }
}

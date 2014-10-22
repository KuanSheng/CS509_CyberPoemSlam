package Words.model;

import java.util.ArrayList;

public class Poem extends Element{
	ArrayList<Word> words = new ArrayList<Word>();
	ArrayList<Row> rows = new ArrayList<Row>();
	
	int x;
    int y;
    int x_last;
    int y_last;
    
    public Poem(int x, int y){
    	this.x = x;
    	this.y = y;
    	words = null;
    }
    
    public void addWord(Word word){
    	words.add(word);
    }
}

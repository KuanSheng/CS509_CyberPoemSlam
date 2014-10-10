package Words.model;

import java.util.ArrayList;

public class Poem {
	ArrayList<Word> words = new ArrayList<Word>();
	int x;
    int y;
    
    public Poem(int x, int y){
    	this.x = x;
    	this.y = y;
    	words = null;
    }
    
    public void addWord(Word word){
    	words.add(word);
    }
}

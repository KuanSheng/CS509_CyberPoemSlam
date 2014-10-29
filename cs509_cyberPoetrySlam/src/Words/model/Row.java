package Words.model;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
public class Row implements Iterable<Word>, Serializable{
ArrayList<Word> words = new ArrayList<Word>();
	
	int x;
	int y;
	int x_last;
	int y_last;
	int height;
	int width;

	public Row(int x, int y, int hight, int width){
      this.x = x;
      this.y = y;
	}
	
	public Row(Word w1,Word w2){
		this.x = w1.getX();
		this.y = w1.getY();
		this.height = w1.getHeight();
		this.width = w1.getWidth()+w2.getWidth();
		this.addWord(w1);
		this.addWord(w2);
	}
	
	public void addWord(Word word){
    	words.add(word);
    }
	
	
	 public int getX(){return this.x;}
     public int getY(){return this.y;}
     public int getWidth(){return this.width;}
     public int getHeight(){return this.height;}
     
     //return all words in the row
     public Iterator<Word> iterator(){
    	 return words.iterator();
     }


}

package Words.model;
import java.util.*;
public class Row implements Iterable<Word>{
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
	
	public Row(Word w1,Word w2,int direction){
		if(direction==1){
			this.x = w1.getX()-w2.getWidth();
			this.y = w1.getY();
		}
		else{
			this.x = w2.getX()-w1.getWidth();
			this.y = w2.getY();
		}
		this.height = w1.getHeight();
		this.width = w1.getWidth()+w2.getWidth();
		this.words.add(w1);
		this.words.add(w2);
	}
	
	public void addWord(Word word){
    	words.add(word);
    	this.width = this.width+word.getWidth();
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

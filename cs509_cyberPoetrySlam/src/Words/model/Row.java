package Words.model;
import java.util.*;
public class Row extends Element implements Iterable<Word>{
ArrayList<Word> words = new ArrayList<Word>();
	
	int x;
	int y;
	int x_last;
	int y_last;
	int height;
	int width;

	public Row(int x, int y, int hight, int width){
	  super.type = 2;
      this.x = x;
      this.y = y;
	}
	
	public Row(Word w1,Word w2,int direction){
		if(direction==1){
			this.x = w1.getX();
			this.y = w1.getY();
			w2.setLocation(this.x+w1.getWidth(),this.y);
			//System.out.println(w1.getX());
			//System.out.println(w2.getX());
		}
		else{
			this.x = w2.getX()-w1.getWidth();
			this.y = w2.getY();
			w1.setLocation(this.x, this.y);
			//System.out.println(w2.getX());
			//System.out.println(w1.getX());
		}
		this.height = w1.getHeight();
		this.width = w1.getWidth()+w2.getWidth();
		this.words.add(w1);
		this.words.add(w2);
		System.out.println(w2.getX());
		System.out.println(w1.getX());
	}
	
	public void addWord(Word word){
    	words.add(word);
    	this.width = this.width+word.getWidth();
    }
	
	public boolean intersection(int x, int y){
		if(x < this.x){return false;}
   	 if(x > (this.x + width)){return false;}
   	 if(y < this.y){return false;}
   	 if(y > (this.y+height)){return false;}
	return true;
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

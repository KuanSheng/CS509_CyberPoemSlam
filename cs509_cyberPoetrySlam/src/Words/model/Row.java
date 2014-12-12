package Words.model;
import java.io.Serializable;
import java.util.*;
public class Row extends Element implements Iterable<Word>, Serializable{
ArrayList<Word> words = new ArrayList<Word>();
    int x;
	int y;
	int x_last;
	int y_last;
	int height;
	int width;
	int WordNumber;
	
	Row formerRow;
	Row nextRow;

	public Row(int x, int y, int height, int width){
	  super.type = 2;
      this.x = x;
      this.y = y;
      this.height=height;
      this.width=width;
      this.WordNumber = 0;
	}
	
	public Row(Word w1,Word w2,int direction){
		if(direction==2){
			this.x = w1.getX();
			this.y = w1.getY();
			w2.setLocation(this.x+w1.getWidth(),this.y);
		}
		else{
			this.x = w2.getX()-w1.getWidth();
			this.y = w2.getY();
			w1.setLocation(this.x, this.y);
		}
		
		this.height = w1.getHeight();
		this.width = w1.getWidth()+w2.getWidth();
		this.words.add(w1);
		this.words.add(w2);
		this.WordNumber =2;
	}
	
	public void addWord(Word word){
    	words.add(word);
    	this.WordNumber++;
    	this.width = this.width+word.getWidth();
    }
	
	public void removeWord(Word word){
		words.remove(word);
		this.WordNumber--;
		this.width = this.width-word.getWidth();
	}
	
	public int getWordNumber(){
		return this.WordNumber;
	}
	
	public Word getFirstWord(){
		for(Word w:words){
			if(w.getX() == this.getX()){
				return w;
			}
		}
		return null;
	}
	
	public Word getLastWord(){
		for(Word w:words){
			if(w.getX()+w.getWidth() == this.getX()+this.getWidth()){
				return w;
			}
		}
		
		return null;
	}
	
	public ArrayList<Word> getWords(){
		return this.words;
	}
	
	public int getLeftShiftLimit(){
		Row former = this.getFormerRow();
		Row next = this.getNextRow();
		
		Word w1 = null;
		Word w2 = null;
		
		if(former == null&&next == null){
			return 0;
		}
		
		if(former != null&&next == null){
			w1 = former.getFirstWord();
			return w1.getX()+w1.getWidth() - this.width;
		}
		
		if(next != null&&former == null){
		    w2 = next.getFirstWord();
		    return w2.getX()+w2.getWidth() - this.width;
		}
		
		 w1 = former.getFirstWord();
		 w2 = next.getFirstWord();
		int leftLimit = Math.max(w1.getX()+w1.getWidth(), w2.getX()+w2.getWidth());
		
		return leftLimit - this.width;
	}
	
	public int getRightShiftLimit(){
		Row former = this.getFormerRow();
		Row next = this.getNextRow();
		
		Word w1 = null;
		Word w2 = null;
		
		if(former == null&&next == null){
			return 0;
		}
		
		if(former != null&&next == null){
			w1 = former.getLastWord();
			return w1.getX();
		}
		
		if(next != null&&former == null){
		    w2 = next.getLastWord();
		    return w2.getX();
		}
		
		w1 = former.getLastWord();
	    w2 = next.getLastWord();
		int rightLimit = Math.min(w1.getX(), w2.getX());
		
		return rightLimit;
	}
	
	
	public boolean intersection(int x, int y){
	 if(x < this.x){return false;}
   	 if(x > (this.x + width)){return false;}
   	 if(y < this.y){return false;}
   	 if(y > (this.y+height)){return false;}
	 return true;
	}
	
	public void setLocation(int x, int y,int rx,int ry){
		this.x = x;
		this.y = y;
		int deltax = x - rx;
		int deltay = y - ry;
		for(Word w: words){
            w.setLocation(w.getX()+deltax,w.getY()+deltay);
		}
		
	}
	
	public void setLocationAfterConnection(int x,int y){
		this.x = x;
		this.y = y;
	}

	 public int getX(){return this.x;}
     public int getY(){return this.y;}
     public int getWidth(){return this.width;}
     public int getHeight(){return this.height;}
     
     public Row getNextRow(){
    	 return this.nextRow;
     }
     public Row getFormerRow(){
    	 return this.formerRow;
     }
     public void setNextRow(Row r){
    	 this.nextRow = r;
     }
     public void setFormerRow(Row r){
    	 this.formerRow = r;
     }
     
     //return all words in the row
     public Iterator<Word> iterator(){
    	 return words.iterator();
     }

    /**
     * Jun
     */
    public String toString(){
        StringBuffer sb = new StringBuffer();
        for(Word w : words){
            sb.append(w+" ");
        }
        return sb.toString();
    }
}

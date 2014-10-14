package Words.model;

import java.util.ArrayList;

public class Row extends Element{
	
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
		this.x_last = x;
		this.y_last = y;
		this.height = hight;
		this.width = width;
		words=null;
	}
	
	public void addWord(Word word){
    	words.add(word);
    	this.width = this.width + word.width;
    }
	
	public void move(int x, int y){
		this.x_last = this.x;
		this.y_last = this.y;
		this.x = x;
		this.y = y;
	}
	
	public Intersection getIntersectionWord(Word word){
		
		Intersection intersection = new Intersection();
		
		// no intersection
		if((this.x + this.width < word.x) ||(this.x > word.x + word.width)|| 
		   (this.y < (word.y - word.height))||(this.y - this.height) > word.y){
			
			intersection.type = -1;
		}
		
		// x left intersection and y no determined
		if((this.x + this.width > word.x ) && (this.x + this.width < word.x + word.width) && 
				(this.x < word.x)){
			if(this.y == word.y){     
				intersection.type = 1;
				intersection.e.type = 2;
				intersection.e.x = word.x - this.width;
				intersection.e.y = word.y;
			}
			
			else if((this.y < word.y) && (this.y > word.y - word.height)){
				if(this.y - (word.y - word.height) > (this.x + this.width - word.x)){
					
				}
			}
		}
		
		return intersection;
		
	}
}

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
    }
	
	public void move(int x, int y){
		this.x_last = this.x;
		this.y_last = this.y;
		this.x = x;
		this.y = y;
	}
	
	public Intersection getIntersectionWord(Word word){
		
		Intersection intersection = new Intersection();

		for(int i = 0;i < (this.words).size(); i ++){
			Word wordInRow = (this.words).get(i);
			intersection = wordInRow.getIntersectionInRow(word);

			//the leftmost word in the row
			if(i == 0){
				if(intersection.type ==1 || intersection.type == 3 || intersection.type ==4) break;
				if (intersection.type == 2) {
					if(this.y == word.y) {
						if(word.x + word.width > this.x + this.width){
							break;
						}
						else {
							intersection.type = 5;
							break;
						}
					}
					
					if(word.y > this.y){
						intersection.type = 3;
						intersection.e.type = 3;
						intersection.e.x = this.x;
						intersection.e.y = this.y + word.height;
						break;
					}
					
					if(word.y < this.y){
						intersection.type = 4;	
						intersection.e.type = 3;
						intersection.e.x = this.x;
						intersection.e.y = this.y;
						break;
					}
			}
				
				
				if(intersection.type == 6){
					if(word.x + word.width > this.x + this.width){
						intersection.type = 5;
						break;
					}
					else {
						intersection.type = 1;
						intersection.e.type = 2;
						intersection.e.x = this.x - word.width;
						intersection.e.y = this.y;
						break;
					}
				}
		}
			
			if( i>0 && i< (this.words).size()){
				if(intersection.type ==3 || intersection.type == 4) break;
				if(intersection.type == 2) {
					if(this.y == word.y) {
						if(word.x + word.width > this.x + this.width){
							break;
						}
						else {
							intersection.type = 5;
							break;
						}
					}
					
					if(word.y > this.y){
						intersection.type = 3;
						intersection.e.type = 3;
						intersection.e.x = this.x;
						intersection.e.y = this.y + word.height;
						break;
					}
					
					if(word.y < this.y){
						intersection.type = 4;	
						intersection.e.type = 3;
						intersection.e.x = this.x;
						intersection.e.y = this.y;
						break;
					}
				}
					
			}
				

	     if(intersection.type == -1) continue;
         if(intersection.type == 5) break;
		}
		return intersection;	
	}
	

	public Intersection getIntersection(Row row){
   	 
   	 Intersection intersection = new Intersection();
   	 
   	// no intersection
		if((this.x + this.width < row.x) ||(this.x > row.x + row.width)|| 
		   (this.y < (row.y - row.height))||(this.y - this.height) > row.y){
			
			intersection.type = -1;
		}
		
		// x left intersection and y no determined
		if((row.x + row.width > this.x ) && (row.x + row.width < this.x + this.width) && 
				(row.x < this.x)){
			
		
			// y are equal then left intersection
			if(this.y == row.y){     
				intersection.type = 1;
				intersection.e.type = 2;
				intersection.e.x = this.x - row.width;
				intersection.e.y = this.y;
			}
			
			// y overlaps with bottom part
			if((row.y < this.y) && (row.y > this.y - this.height)){
				// y overlap more than x overlap then left intersection
				if(row.y - (this.y - this.height) >= (row.x + row.width - this.x)){
				intersection.type = 1;	
				intersection.e.type = 2;
				intersection.e.x = this.x - row.width;
				intersection.e.y = this.y;		
			}
				// y overlap less than x overlap then bottom intersection
				else {
					intersection.type = 4;	
					intersection.e.type = 3;
					intersection.e.x = this.x;
					intersection.e.y = this.y;	
				}
			}		
					
			// row y overlaps with top part of row y
			if((row.y > this.y) && (row.y - row.height < this.y)){
				//y overlap more than x overlap then left intersection
				if( (this.y-(row.y - row.height)) >= (row.x + row.width - this.x)){
					intersection.type = 1;	
					intersection.e.type = 2;
					intersection.e.x = this.x - row.width;
					intersection.e.y = this.y;
				}
				// y overlap less than x overlap then top intersection
				else {
					intersection.type = 3;
					intersection.e.type = 3;
					intersection.e.x = this.x;
					intersection.e.y = this.y + row.height;	
				}	
			}
		}
		
		//x right intersection and y no determined
		if((row.x < this.x + this.width ) && (row.x + row.width > this.x + this.width) && 
				(row.x > this.x)){
			
		
			// y are equal then right intersection
			if(this.y == row.y){     
				intersection.type = 2;
				intersection.e.type = 2;
				intersection.e.x = this.x;
				intersection.e.y = this.y;
			}
			
			//row y overlaps with bottom part of row y
			if((row.y < this.y) && (row.y > this.y - this.height)){
				// y overlap more than x overlap then right intersection
				if(row.y - (this.y - this.height) >= (this.x + this.width - row.x)){
				intersection.type = 2;	
				intersection.e.type = 2;
				intersection.e.x = this.x;
				intersection.e.y = this.y;		
			}
				// y overlap less than x overlap then bottom intersection
				else {
					intersection.type = 4;	
					intersection.e.type = 3;
					intersection.e.x = this.x;
					intersection.e.y = this.y;	
				}
			}		
					
			// row y overlaps with top part of row y
			if((row.y > this.y) && (row.y - row.width < this.y)){
				//y overlap more than x overlap then right intersection
				if( (this.y-(row.y - row.height)) >= (this.x + this.width - row.x)){
					intersection.type = 2;	
					intersection.e.type = 2;
					intersection.e.x = this.x;
					intersection.e.y = this.y;
				}
				// y overlap less than x overlap then top intersection
				else {
					intersection.type = 3;
					intersection.e.type = 3;
					intersection.e.x = this.x;
					intersection.e.y = this.y + row.height;	
				}	
			}
		}
		
		//x overlap totally and y not determined
				if((this.x >= row.x && this.x + this.width <= row.x + row.width) ||
						(this.x <= row.x && this.x + this.width >= row.x + row.width)){
					// y overlap with top part of row then top intersection
					if((this.y > row.y) && (this.y - this.width < row.y)){
						intersection.type = 3;
						intersection.e.type = 3;
						intersection.e.x = row.x;
						intersection.e.y = row.y + this.height;
					}
					//y overlap with bottom part of row then bottom intersection
					if((this.y < row.y) && (this.y > row.y - row.height)){
						intersection.type = 4;	
						intersection.e.type = 3;
						intersection.e.x = row.x;
						intersection.e.y = row.y;	
					}
					if(this.y == row.y){
						intersection.type = 5;
					}
				}
		
		 return intersection;
    }
	

	public Element select(int top, int bottom, int left, int right){       //select multiple words 
		Element element = new Element();
		
		
		return element;
	}
	
	
	public Element select(int x, int y){                                  //select single word
		Element element = new Element();
		
		return element;
	}
}

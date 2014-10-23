package Words.model;

public class Word extends Element{
	 int x;
	 int y;
	 int x_last;
	 int y_last;
	 
     final String value;
     final int width;
     final int height;
     final int wordType;
     
     public int getX(){return this.x;}
     public int getY(){return this.y;}
     public int getWidth(){return this.width;}
     public int getHeight(){return this.height;}
     public String getValue(){return this.value;}
     
     
     public Word(int x,int y,int width,int height,String value, int wordType){
    	 this.x = x;
    	 this.y = y;
    	 this.x_last = x;
    	 this.y_last = y;
    	 this.width = width;
    	 this.height = height;
    	 this.value = value;
    	 this.wordType = wordType;
      }
     
     public boolean isProtected(){
    	 if(this.y < 540){
    		 return false;
    	 }
    	 return true;
     }
     
     //mouse click in the word
     public boolean intersection(int x,int y){
    	 if(x < this.x){return false;}
    	 if(x > (this.x + width)){return false;}
    	 if(y < this.y){return false;}
    	 if(y > (this.y+height)){return false;}
    	 return true;
     }
     
     
     public Intersection getIntersection(Word word){
    	 
    	 Intersection intersection = new Intersection();
    	 
    	// no intersection
 		if((this.x + this.width < word.x) ||(this.x > word.x + word.width)|| 
 		   (this.y < (word.y - word.height))||(this.y - this.height) > word.y){
 			
 			intersection.type = -1;
 		}
 		
 		// x left intersection and y no determined
		if((word.x + word.width > this.x ) && (word.x + word.width < this.x + this.width) && 
				(word.x < this.x)){
			
		
			// y are equal then left intersection
			if(this.y == word.y){     
				intersection.type = 1;
				intersection.e.type = 2;
				intersection.e.x = this.x - word.width;
				intersection.e.y = this.y;
			}
			
			// y overlaps with bottom part
			if((word.y < this.y) && (word.y > this.y - this.height)){
				// y overlap more than x overlap then left intersection
				if(word.y - (this.y - this.height) >= (word.x + word.width - this.x)){
				intersection.type = 1;	
				intersection.e.type = 2;
				intersection.e.x = this.x - word.width;
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
					
			// row y overlaps with top part of word y
			if((word.y > this.y) && (word.y - word.height < this.y)){
				//y overlap more than x overlap then left intersection
				if( (this.y-(word.y - word.height)) >= (word.x + word.width - this.x)){
					intersection.type = 1;	
					intersection.e.type = 2;
					intersection.e.x = this.x - word.width;
					intersection.e.y = this.y;
				}
				// y overlap less than x overlap then top intersection
				else {
					intersection.type = 3;
					intersection.e.type = 3;
					intersection.e.x = this.x;
					intersection.e.y = this.y + word.height;	
				}	
			}
		}
		
		//x right intersection and y no determined
		if((word.x < this.x + this.width ) && (word.x + word.width > this.x + this.width) && 
				(word.x > this.x)){
			
		
			// y are equal then right intersection
			if(this.y == word.y){     
				intersection.type = 2;
				intersection.e.type = 2;
				intersection.e.x = this.x;
				intersection.e.y = this.y;
			}
			
			//row y overlaps with bottom part of word y
			if((word.y < this.y) && (word.y > this.y - this.height)){
				// y overlap more than x overlap then right intersection
				if(word.y - (this.y - this.height) >= (this.x + this.width - word.x)){
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
					
			// row y overlaps with top part of word y
			if((word.y > this.y) && (word.y - word.width < this.y)){
				//y overlap more than x overlap then right intersection
				if( (this.y-(word.y - word.height)) >= (this.x + this.width - word.x)){
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
					intersection.e.y = this.y + word.height;	
				}	
			}
		}
		
		//x overlap totally and y not determined
				if((this.x >= word.x && this.x + this.width <= word.x + word.width) ||
						(this.x <= word.x && this.x + this.width >= word.x + word.width)){
					// y overlap with top part of word then top intersection
					if((this.y > word.y) && (this.y - this.width < word.y)){
						intersection.type = 3;
						intersection.e.type = 3;
						intersection.e.x = word.x;
						intersection.e.y = word.y + this.height;
					}
					//y overlap with bottom part of word then bottom intersection
					if((this.y < word.y) && (this.y > word.y - word.height)){
						intersection.type = 4;	
						intersection.e.type = 3;
						intersection.e.x = word.x;
						intersection.e.y = word.y;	
					}
				}
		
		 return intersection;
     }
     
     
     public Intersection getIntersectionInWord(Word word){
    	 
    	 Intersection intersection = new Intersection();
    	 
    	 intersection = this.getIntersection(word);
    	 
			// two words overlap totally
			if(this.y == word.y){
				
				if((this.x >= word.x && this.x + this.width <= word.x + word.width) ||
						(this.x < word.x && this.x + this.width > word.x + word.width)){
				intersection.type = 5;		
			}
		}

    	 return intersection;
     }

     public Intersection getIntersectionInRow(Word word){
    	 Intersection intersection = new Intersection();
    	 
    	 intersection = this.getIntersection(word);
    	 
    	 if(this.y == word.y){
    		 if(this.x > word.x && this.x + this.width < word.x + word.width){
    			 intersection.type = 6;
    		 }
    		 
    		 if(this.x <= word.x && this.x + this.width >= word.x + word.width){
    			 intersection.type = 5;
    		 }
    	 }
    	 return intersection;    	 
     }
     
     public void move(int x_new, int y_new ){
    	 this.x = x_new;
    	 this.y = y_new;
     }
}

 
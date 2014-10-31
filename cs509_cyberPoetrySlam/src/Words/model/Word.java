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
    		 return true;
    	}
    	 return false;
     }
     
     //mouse click in the word
     public boolean intersection(int x,int y){
    	 if(x < this.x){return false;}
    	 if(x > (this.x + width)){return false;}
    	 if(y < this.y){return false;}
    	 if(y > (this.y+height)){return false;}
    	 return true;
     }
     
     
    public boolean overlap(Word w){
    	 if((this.x+this.width)>w.getX() && (this.x+this.width)<(w.getX()+w.width) && (w.getY()+w.getHeight())>this.y&&(w.getY()+w.getHeight())<(this.y+this.height)){
    		 return true;
    	 }
    	 else if((this.x+this.width)>w.getX()&&(this.x+this.width)<(w.getX()+w.width)&&(this.y+this.height)>w.getY()&&(this.y+this.height)<(w.getY()+w.height)){
    		 return true; 
    	 }
    	 else  if((w.getX()+w.getWidth())>this.x&&(w.getX()+w.getWidth())<(this.x+this.width)&&(this.y+this.height)>w.getY()&&(this.y+this.height)<(w.getY()+w.height)){
    		 return true;
    	 }
    	 else  if((w.getX()+w.getWidth()>this.x)&&(w.getX()+w.getWidth())<(this.x+this.width)&&(w.getY()+w.getHeight()>this.y)&&(w.getY()+w.getHeight())<(this.y+this.height)){
    		 return true; 
    	 }
    	 else if((w.getX()+w.getWidth()>this.x)&&(w.getX()+w.getWidth()<this.x+this.width)&& (w.getY()==this.y)){
    		 return true;
    	 }
    	 else if((this.x+this.width>w.getX()) && (this.x+this.width<w.getX()+w.getWidth()) && (this.y==w.getY())){
    		 return true;
    	 }
    	 else return false;
     }
     public void move(int x_new, int y_new ){
    	 this.x = x_new;
    	 this.y = y_new;
     }
}

 
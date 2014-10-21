package Words.model;

public class Word extends Element{
	 private int x;
	 private int y;
	 private int x_last;
	 private int y_last;
	 
     final String value;
     final int width;
     final int height;
     
     public Word(int x,int y,int width,int height,String value){
    	 this.x = x;
    	 this.y = y;
    	 this.x_last = x;
    	 this.y_last = y;
    	 this.width = width;
    	 this.height = height;
    	 this.value = value;
     }
     
     public void move(int x_new, int y_new){
    	 this.x_last = this.x;
    	 this.y_last = this.y;
    	 this.x = x_new;
    	 this.y = y_new;
     }
     
     public boolean intersection(int x,int y){
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
     public String getValue(){return this.value;}
     
}


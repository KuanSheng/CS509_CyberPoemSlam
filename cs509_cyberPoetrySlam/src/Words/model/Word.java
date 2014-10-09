package Words.model;

public class Word {
	 int x;
	 int y;
	 
     final String value;
     final int width;
     final int height;
     
     public Word(int x,int y,int width,int height,String value){
    	 this.x = x;
    	 this.y = y;
    	 this.width = width;
    	 this.height = height;
    	 this.value = value;
     }
     
     public int getX(){return this.x;}
     public int getY(){return this.y;}
     public int getWidth(){return this.width;}
     public int getHeight(){return this.height;}
     public String getValue(){return this.value;}
     
     public void setLocation(int x, int y ){
    	 this.x = x;
    	 this.y = y;
     }
}


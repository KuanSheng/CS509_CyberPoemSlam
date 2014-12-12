package Words.model;

import java.io.IOException;
import java.io.Serializable;

public class Word extends Element implements Serializable{
	 int x;
	 int y;

    String value;
    int width;
    int height;
    int wordType;

    public static final int ADJ_INT = 0;
    public static final int ADV_INT = 1;
    public static final int NOUN_INT = 2;
    public static final int VERB_INT = 3;


    public static final String VERB_STRING = "verb";
    public static final String ADJ_STRING = "adj";
    public static final String NOUN_STRING = "noun";
    public static final String ADV_STRING = "adv";

    public static final String[] TYPE_INT_TO_STRING = {ADJ_STRING, ADV_STRING, NOUN_STRING, VERB_STRING};
     
     public Word(int x,int y,int width,int height,String value,int wordType){
    	 super.type = 1;
    	 this.x = x;
    	 this.y = y;
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
     
     public boolean intersection(int x,int y){
    	 if(x < this.x){return false;}
    	 if(x > (this.x + width)){return false;}
    	 if(y < this.y){return false;}
    	 if(y > (this.y+height)){return false;}
    	 return true;
     }
     
     public boolean overlap(Word w){
    	 if((this.x+this.width)>w.getX()&&(this.x+this.width)<(w.getX()+w.width)&&(w.getY()+w.getHeight())>this.y&&(w.getY()+w.getHeight())<(this.y+this.height)){
    		 return true;
    	 }
    	 if((this.x+this.width)>w.getX()&&(this.x+this.width)<(w.getX()+w.width)&&(this.y+this.height)>w.getY()&&(this.y+this.height)<(w.getY()+w.height)){
    		 return true; 
    	 }
    	 if((w.getX()+w.getWidth())>this.x&&(w.getX()+w.getWidth())<(this.x+this.width)&&(this.y+this.height)>w.getY()&&(this.y+this.height)<(w.getY()+w.height)){
    		 return true;
    	 }
    	 if((w.getX()+w.getWidth()>this.x)&&(w.getX()+w.getWidth())<(this.x+this.width)&&(w.getY()+w.getHeight()>this.y)&&(w.getY()+w.getHeight())<(this.y+this.height)){
    		 return true; 
    	 }
    	 if((w.getX()+w.getWidth()>this.x)&&(w.getX()+w.getWidth()<this.x+this.width)&&w.getY()==this.y){
    		 return true;
    	 }
    	 if((this.x+this.width>w.getX())&&(this.x+this.width<w.getX()+w.getWidth())&&this.y==w.getY()){
    		 return true;
    	 }
    	 return false;
     }
     
     public boolean overlapRow(Row w){
    	 if((this.x+this.width)>w.getX()&&(this.x+this.width)<(w.getX()+w.width)&&(w.getY()+w.getHeight())>this.y&&(w.getY()+w.getHeight())<(this.y+this.height)){
    		 return true;
    	 }
    	 if((this.x+this.width)>w.getX()&&(this.x+this.width)<(w.getX()+w.width)&&(this.y+this.height)>w.getY()&&(this.y+this.height)<(w.getY()+w.height)){
    		 return true; 
    	 }
    	 if((w.getX()+w.getWidth())>this.x&&(w.getX()+w.getWidth())<(this.x+this.width)&&(this.y+this.height)>w.getY()&&(this.y+this.height)<(w.getY()+w.height)){
    		 return true;
    	 }
    	 if((w.getX()+w.getWidth()>this.x)&&(w.getX()+w.getWidth())<(this.x+this.width)&&(w.getY()+w.getHeight()>this.y)&&(w.getY()+w.getHeight())<(this.y+this.height)){
    		 return true; 
    	 }
    	if((w.getX()+w.getWidth()>this.x)&&(w.getX()+w.getWidth()<this.x+this.width)&&w.getY()==this.y){
    		 return true;
    	 }
    	 if((this.x+this.width>w.getX())&&(this.x+this.width<w.getX()+w.getWidth())&&this.y==w.getY()){
    		 return true;
    	 }
    	 return false;
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

     public int getWordType() {
    	 return this.wordType;
    }
     
    public String toString(){
        return value;
    }

}
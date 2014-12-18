package Words.model;

import java.io.IOException;
import java.io.Serializable;

public class Word extends Element implements Serializable{
    public static final int TYPE_COUNT = 10;
    public static final int HIGHT = 14;
    int x;
	 int y;

    String value;
    int width;
    int height;
    int wordType;

    public static final int ADJ_INT         = 0;
    public static final int ADV_INT         = 1;
    public static final int CONJUNCTION_INT = 2;
    public static final int DETERMINER_INT  = 3;
    public static final int NOUN_INT        = 4;
    public static final int NUMBER_INT      = 5;
    public static final int PRONOUN_INT     = 6;
    public static final int PREPOSITION_INT = 7;
    public static final int SUFFIX_INT      = 8;
    public static final int VERB_INT        = 9;



    public static final String ADJ_STRING           = "adjective";
    public static final String ADV_STRING           = "adverb";
    public static final String CONJUNCTION_STRING   = "conjunction";
    public static final String DETERMINER_STRING    = "determiner";
    public static final String NOUN_STRING          = "noun";
    public static final String NUBER_STRING         = "number";
    public static final String PRONOUN_STRING       = "pronoun";
    public static final String PREPOSITION_STRING   = "preposition";
    public static final String SUFFIX_STRING        = "suffix";
    public static final String VERB_STRING          = "verb";

    public static final String[] TYPE_INT_TO_STRING = {ADJ_STRING, ADV_STRING,
            CONJUNCTION_STRING,
            DETERMINER_STRING,NOUN_STRING,
            NUBER_STRING,PRONOUN_STRING,
            PREPOSITION_STRING,SUFFIX_STRING,VERB_STRING};

     /**Constructor**/
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
     
     /**use x,y to find word**/
     public boolean intersection(int x,int y){
    	 if(x < this.x){return false;}
    	 if(x > (this.x + width)){return false;}
    	 if(y < this.y){return false;}
    	 if(y > (this.y+height)){return false;}
    	 return true;
     }
     
     /**check if overlap with another word**/
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
     
     /**check if overlap with a row**/
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
     /**return some attributes of word**/
     public int getX(){return this.x;}
     public int getY(){return this.y;}
     public int getWidth(){return this.width;}
     public int getHeight(){return this.height;}
     public String getValue(){return this.value;}
     
     /**update location**/
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

    public static final int getTypeInt(String s) {
        if(ADJ_STRING.equals(s)) return ADJ_INT;
        if(ADV_STRING.equals(s)) return ADV_INT;

        if(CONJUNCTION_STRING.equals(s)) return CONJUNCTION_INT;
        if(DETERMINER_STRING.equals(s)) return DETERMINER_INT;
        if(NUBER_STRING.equals(s)) return NUMBER_INT;
        if(NOUN_STRING.equals(s)) return NOUN_INT;
        if(PRONOUN_STRING.equals(s)) return PRONOUN_INT;
        if(PREPOSITION_STRING.equals(s)) return PREPOSITION_INT;
        if(SUFFIX_STRING.equals(s)) return SUFFIX_INT;

        if(NOUN_STRING.equals(s)) return NOUN_INT;
        if(VERB_STRING.equals(s)) return VERB_INT;
        return -1;
    }
}
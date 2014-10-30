package Words.model;

import java.util.ArrayList;

public class Poem extends Element{
	ArrayList<Row> rows = new ArrayList<Row>();
	int x;
    int y;
    
    public Poem(int x, int y){
    	super.type=3;
    	this.x = x;
    	this.y = y;
    	rows = null;
    }
    
    public boolean intersection(int x, int y){
    	for(Row r:rows){
    		if(r.intersection(x, y)){
    			return true;
    		}
    	}
    	return false;
    }
    public Poem(Word w1, Word w2,int direction){
    	Row row = new Row(w1,w2,direction);
    	this.addRow(row);
    	this.x = row.getX();
    	this.y = row.getY();
    }
    
    public ArrayList<Row> getRows(){
    	return this.rows;
    }
    public void addRow(Row row){
    	rows.add(row);
    }
    
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    
    public void setLocation(int x, int y ){
   	 this.x = x;
   	 this.y = y;
   	 for(Row r:rows){
   		 r.setLocation(x,y);
   	 }
    }
}

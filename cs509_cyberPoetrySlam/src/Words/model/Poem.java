package Words.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Poem extends Element implements Serializable{
	ArrayList<Row> rows = new ArrayList<Row>();
	int x;
    int y;
    int RowNumber;
   
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
    
    public Poem(Poem p1, Poem p2, int direction){
    	//p1 on top
    	if(direction == 1){
    		this.x = p2.getX();
    		this.y = p2.getY() - 14*p1.getRowNumber();
    		
    		p1.setLocation(this.x, this.y, p1.getX(), p1.getY());
    		
    		for(Row r: p1.getRows()){
    			this.addRow(r);
    		}
    		
    		for(Row r: p2.getRows()){
    			this.addRow(r);
    		}
    	}
    	//p2 on top
    	else{
    		this.x = p2.getX();
    		this.y = p2.getY();
    		
    		p1.setLocation(this.x, this.y+14*p1.getRowNumber(), p1.getX(), p1.getX());
    		
    		for(Row r: p1.getRows()){
    			this.addRow(r);
    		}
    		
    		for(Row r: p2.getRows()){
    			this.addRow(r);
    		}
    		
    	}
    }
  
    public ArrayList<Row> getRows(){
    	return this.rows;
    }
    
    public void addRow(Row row){
    	rows.add(row);
    	this.RowNumber++;
    }
    
    public void addWord(Word w){
    	for(Row r:rows){
    		if(w.overlapRow(r)){
    			r.addWord(w);
    		}
    	}
    }
    
    public Row getOverlapRow(Word w){
    	for(Row r:rows){
    		if(w.overlapRow(r)){
    			return r;
    		}
    	}
    	return null;
    }
    
    public void removeRow(Row r){
    	rows.remove(r);
    	this.RowNumber--;
    }
    
    public int getRowNumber(){
    	return this.RowNumber;
    }
    
    public Row getFirstRow(){
    	for(Row r:rows){
    		if(r.getX() == this.x&&r.getY() == this.y){
    			return r;
    		}
    	}
    	return null;
    }
    
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    
    public void setLocationAfterConnection(int x,int y){
    	this.x = x;
    	this.y = y;
    }
    
    public void setLocation(int x, int y, int ox, int oy){
   	 this.x = x;
   	 this.y = y;
   	 
   	 int deltax = x-ox;
   	 int deltay = y-oy;
   	 
   	 for(Row r:rows){
   		 r.setLocation(r.getX()+deltax,r.getY()+deltay,r.getX(),r.getY());
   	 }
   	 
    }
}

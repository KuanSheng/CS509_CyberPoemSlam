package Words.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Poem extends Element implements Serializable{
	ArrayList<Row> rows = new ArrayList<Row>();
	int x;
    int y;
    int rowNumber;
    
    public boolean equals(Poem p){
//    	boolean result = x == p.x && y == p.y && rowNumber == p.rowNumber &&
//    			rows.equals(p.rows);
    	if( x != p.x){
    		System.out.println("wrong x " + x + " vs " + p.x + rows.get(0).getFirstWord() + " " + p.getRows().get(0).getFirstWord() );
    		return false;
    	}
    	if(y != p.y){
    		System.out.println("wrong y");
    		return false;
    	}
    	if(rowNumber != p.rowNumber){
    		System.out.println("wrong row nuber");
    		return false; 
    	}
    	if(!rows.equals(p.rows)){
    		System.out.println("rows not the same");
    		
    	}
    	System.out.println("pomes the same");
    	return true;
    }
   
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
    	this.rowNumber++;
    	this.x = row.getX();
    	this.y = row.getY();
    }
    
    public ArrayList<Row> getRows(){
    	return this.rows;
    }
    public void addRow(Row row){
    	rows.add(row);
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
    	this.rowNumber--;
    }
    
    public int getRowNumber(){
    	return this.rowNumber;
    }
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    
    public void setLocationAfterConnection(int x,int y){
    	this.x = x;
    	this.y = y;
    }
    
    public void setLocation(int x, int y){
   	 this.x = x;
   	 this.y = y;
   	 for(Row r:rows){
   		 r.setLocation(x,y);
   	 }
   	 
    }
}

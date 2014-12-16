package Words.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Poem extends Element implements Serializable{
	ArrayList<Row> rows = new ArrayList<Row>();
	int x;
    int y;
    int max_x = 0;
    int max_y = 0;
    int min_x;
    int min_y;
    int RowNumber;
    
    Row FirstRow = null;
    Row LastRow = null;
   
    public Poem(int x, int y){
    	this.x = x;
    	this.y = y;
    	this.RowNumber = 0;
    	//rows = null;
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
    	row.setNextRow(null);
    	row.setFormerRow(null);
    	
    	this.addRow(row);
    	this.FirstRow = row;
    	this.LastRow = row;
    	this.x = row.getX();
    	this.y = row.getY();
    	this.min_x = this.x;
    	this.min_y = this.y;
    	this.max_x = this.x+row.getWidth();
    	this.max_y = this.y+row.getHeight();
    }
    
    public Poem(Poem p1, Poem p2, int direction){
    	//p1 on top
    	if(direction == 1){
    		this.x = p2.getX();
    		this.y = p2.getY() - 14*p1.getRowNumber();
    		this.FirstRow = p1.getFirstRow();
    		this.LastRow = p2.getLastRow();
    		
    		p1.setLocation(this.x, this.y, p1.getX(), p1.getY());
    		p1.getLastRow().setNextRow(p2.getFirstRow());
    		p2.getFirstRow().setFormerRow(p1.getLastRow());
    	
    		for(Row r: p1.getRows()){
    			this.addRow(r);
    			if(this.x + r.getWidth()  > this.max_x){
    				this.max_x = this.x + r.getWidth();
    			}
    		}
    		
    		for(Row r: p2.getRows()){
    			this.addRow(r);
    			if(this.x + r.getWidth()  > this.max_x){
    				this.max_x = this.x + r.getWidth();
    			}
    			
    		}
    		
    	}
    	//p2 on top
    	else{
    		this.x = p2.getX();
    		this.y = p2.getY();
    		this.FirstRow = p2.getFirstRow();
    		this.LastRow = p1.getLastRow();
    		
    		//set row location and its next and former
    		p1.setLocation(this.x, this.y+14*p2.getRowNumber(), p1.getX(), p1.getY());
    		p2.getLastRow().setNextRow(p1.getFirstRow());
    		p1.getFirstRow().setFormerRow(p2.getLastRow());
    		
    		for(Row r: p1.getRows()){
    			this.addRow(r);
    			if(this.x + r.getWidth()  > this.max_x){
    				this.max_x = this.x + r.getWidth();
    			}
    		}
    		
    		for(Row r: p2.getRows()){
    			this.addRow(r);
    			if(this.x + r.getWidth()  > this.max_x){
    				this.max_x = this.x + r.getWidth();
    			}
    		}
    	}
    	
    	this.min_x = this.x;
    	this.min_y = this.y;
    	this.max_y = this.y + 14*this.RowNumber;
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
    	this.min_x = this.x;
    	this.min_y = this.y;
    }
    
    public void setLocation(int x, int y, int ox, int oy){
   	 this.x = x;
   	 this.y = y;
   	 
   	 int deltax = x-ox;
   	 int deltay = y-oy;
   	 
   	 this.min_x = this.x;
   	 this.min_y = this.y;
   	 this.max_x = this.max_x + deltax;
   	 this.max_y = this.max_y + deltay;
   	 
   	 for(Row r:rows){
   		 r.setLocation(r.getX()+deltax,r.getY()+deltay,r.getX(),r.getY());
   	   }
   	 }
    
    public Row getLastRow(){
    	return this.LastRow;
    }
    
    public int getMax_x(){
    	int max = 0;
    	for(Row r:rows){
    		if(r.getX()+r.getWidth()>max){
    			max = r.getX()+r.getWidth();
    		}
    	}
    	return max;
    }
    
    
    public int getMin_x(){
    	int min = this.x;;
    	for(Row r:rows){
    		if(r.getX() < min){
    			min = r.getX();
    		}
    	}
    	return min;
    }
    
    public int getMax_y(){
    	Row r = this.getLastRow();
    	return r.getY()+r.getHeight();
    }
    
    public int getMin_y(){
    	return this.y;
    }
    
    public void setMax_Position(int x,int y){
    	this.max_x = x;
    	this.max_y = y;
    }
    
    public void setMin_Postion(int x,int y){
    	this.min_x = x;
    	this.min_y = y;
    }

    /**
     * Jun
     * @return string representation of the Poem
     */
    @Override
    public String toString() {
//        return super.toString();
        StringBuffer sb = new StringBuffer();
        for(Row r : rows){
            sb.append(r+"\n");
        }
        return sb.toString();
    }

	public void setLastRow(Row r) {
		// TODO Auto-generated method stub
		this.LastRow = r;
	}

	public Row getRowByWord(Word w) {
		for(Row r:rows){
			for(Word word:r.getWords()){
				if(word.equals(w))
					return r;
			}
		}
		return null;
	}
}

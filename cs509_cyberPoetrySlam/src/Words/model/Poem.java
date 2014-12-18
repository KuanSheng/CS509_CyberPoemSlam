/**created and modified by KuanSheng**/
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

    /**
     * constructor of (empty) poem
     * @param x x location of the new poem
     * @param y y location of the new poem
     */
    public Poem(int x, int y){
    	this.x = x;
    	this.y = y;
    	this.RowNumber = 0;
    	//rows = null;
    }

    /**
     * check if this poem intersects (overlaps with) (x,y)
     * @param x x value of the point/position in concern
     * @param y y value of the point/position in concern
     * @return true if overlaps, false otherwise
     */
    public boolean intersection(int x, int y){
    	for(Row r:rows){
    		if(r.intersection(x, y)){
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * constructor for a poem of two words
     * @param w1 one word in the poem
     * @param w2 the other word in the poem
     * @param direction direction of connectio of the two words -- 1 means w1 comes first, 2 means w2 comes first
     */
    public Poem(Word w1, Word w2,int direction){
    	Row row = new Row(w1,w2,direction);
    	row.setNextRow(null);
    	row.setFormerRow(null);
    	
    	this.addRow(row);
    	//this.FirstRow = row;
    	this.LastRow = row;
    	this.x = row.getX();
    	this.y = row.getY();
    	this.min_x = this.x;
    	this.min_y = this.y;
    	this.max_x = this.x+row.getWidth();
    	this.max_y = this.y+row.getHeight();
    }

    /**
     * constructor for a poem with two existing poems
     * @param p1 one of the poem to form a new poem
     * @param p2 the other poem to form a new poem
     * @param direction direction of the connection/combination -- 1 means p1's on top, 2 means p2's on top
     */
    public Poem(Poem p1, Poem p2, int direction){
    	//p1 on top
    	if(direction == 1){
    		this.x = p2.getX();
    		this.y = p2.getY() - 14*p1.getRowNumber();
    		//this.FirstRow = p1.getFirstRow();
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
            if(p1.getFirstRow() == null){
    			System.out.println("wawaha");
    		 }
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

    /**
     *
     * @return rows of the poem as an arraylist
     */
    public ArrayList<Row> getRows(){
    	return this.rows;
    }

    /**
     * add a new row to the end of the poem
     * @param row the new row to be added
     */
    public void addRow(Row row){
    	rows.add(row);
    	this.RowNumber++;
    }

    /**
     * add a new word into the poem (in case of overlapping)
     * @param w the new word to be added
     */
    public void addWord(Word w){
    	for(Row r:rows){
    		if(w.overlapRow(r)){
    			r.addWord(w);
    		}
    	}
    }

    /**
     * check if some row of the poem overlaps with word w
     * @param w the word in concern
     * @return the row that overlaps with w, null if there isn't one
     */
    public Row getOverlapRow(Word w){
    	for(Row r:rows){
    		if(w.overlapRow(r)){
    			return r;
    		}
    	}
    	return null;
    }

    /**
     * remove a row r from the poem
     * @param r the row to be removed
     */
    public void removeRow(Row r){
    	rows.remove(r);
    	this.RowNumber--;
    }

    /**
     *
     * @return the number of rows in the poem
     */
    public int getRowNumber(){
    	return this.RowNumber;
    }

    /**
     *
     * @return the first row in the poem, null if it's an empty poem
     */
    public Row getFirstRow(){
    	for(Row r:rows){
    		if(r.getX() == this.x&&r.getY() == this.y){
    			System.out.println(r.getX());
    			return r;
    		}
    	}
    	return null;
    }

    /**
     *
     * @return x value of location of the poem
     */
    public int getX(){return this.x;}

    /**
     * return y value of the location of the poem
     */
    public int getY(){return this.y;}

    /**
     * update the location of the poem to (x,y) --  in case of connection to form a bigger poem
     * @param x new x location
     * @param y new y location
     */
    public void setLocationAfterConnection(int x,int y){
    	this.x = x;
    	this.y = y;
    	this.min_x = this.x;
    	this.min_y = this.y;
    }

    /**
     * set location of poem
     * @param x new x location
     * @param y new y location
     * @param ox original x location of the poem before this update
     * @param oy original y location of this poem before this update
     */
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

    /**
     *
     * @return the last row of the poem
     */
    public Row getLastRow(){
    	return this.LastRow;
    }

    /**
     *
     * @return the maximum x location of the poem (of the right most word)
     */
    public int getMax_x(){
    	int max = 0;
    	for(Row r:rows){
    		if(r.getX()+r.getWidth()>max){
    			max = r.getX()+r.getWidth();
    		}
    	}
    	return max;
    }

    /**
     *
     * @return the minimum x location of the poem (of the left most word)
     */
    public int getMin_x(){
    	int min = this.x;;
    	for(Row r:rows){
    		if(r.getX() < min){
    			min = r.getX();
    		}
    	}
    	return min;
    }

    /**
     *
     * @return the maximum y location of the poem (the bottom row/word)
     */
    public int getMax_y(){
    	Row r = this.getLastRow();
    	return r.getY()+r.getHeight();
    }

    /**
     *
     * @return the minimum y location of the poem (the top row/word)
     */
    public int getMin_y(){
    	return this.y;
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

    /**
     * set last row of a poem
     * @param r the new last row
     */
	public void setLastRow(Row r) {
		this.LastRow = r;
	}

    /**
     * get the row that contains word w
     * @param w the word to be found
     * @return the row that contains w, null if there isn't one
     */
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
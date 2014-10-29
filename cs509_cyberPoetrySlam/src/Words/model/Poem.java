package Words.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Poem extends Element implements Serializable{
	ArrayList<Row> rows = new ArrayList<Row>();
	int x;
    int y;
    
    public Poem(int x, int y){
    	this.x = x;
    	this.y = y;
    	rows = null;
    }
    
    public Poem(Word w1, Word w2){
    	Row row = new Row(w1,w2);
    	this.addRow(row);
    	this.x = row.getX();
    	this.y = row.getY();
    }
    
    public void addRow(Row row){
    	rows.add(row);
    }
}

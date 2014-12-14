//package Words.controller;
package Words.controller;
import java.awt.Point;
import java.awt.event.*;
import java.util.*;

import Words.model.*;
import Words.view.*;

public class WordMoveController extends MouseAdapter{
	final Model model;
	final ApplicationCanvas panel;
	final Board b;
	Point anchor;
	int deltaX;
	int deltaY;
	
	int originalx;
	int originaly;
	int ox;
	int oy;
	
	boolean buildFlag = false;
	boolean RowFlag = false;
	
	public WordMoveController(Model model,ApplicationCanvas panel){
		this.model = model;
		this.panel = panel;
		this.b = model.getBoard();
	}
	
	/**mouse pressed**/
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		anchor = e.getPoint();
		RowFlag = false;
		
		// no board? no behavior!
	    if (model == null) { return;}
		
		//model.setSelectedPoem(null);
		//model.setSelectedRow(null);
		
		Board board = model.getBoard();
		
		
		if (e.getButton()==MouseEvent.BUTTON3) {
			this.generateNewWord(e.getX(), e.getY());
			return;
		}
	
        if(e.getClickCount() == 2){
        	//must in the protected area
        	this.disconnectWord(x, y);
        	return;
        }
        
        // select a word
		Word s = board.findWord(anchor.x, anchor.y);
		
		if (s != null) {
			setSelectedWord(s);
			panel.repaint();
			return;
		}
		
		//shift row
		if(board.findRow(anchor.x, anchor.y, model.getSelectedRow())){
			setRowFlag(model.getSelectedRow());
			return;
		}
		
	    //select a poem
		Poem p = board.findPoem(anchor.x,anchor.y);
		
		if(p != null){
			setSelectedPoem(p);
			panel.repaint();
			return;
		}
		
		if(buildSelectionArea(anchor.x,anchor.y)){
			return;
		}
		
		model.setSelected(null);
		model.setSelectedPoem(null);
		model.setSelectedRow(null);
	}
	
    /**mouse released**/
	public void mouseReleased(MouseEvent e){
		if(this.release()){
		panel.repaint();}
		else{
			return;
		}
	}
	
	/**mouse dragged**/
	public void mouseDragged(MouseEvent e){
		if(drag(e.getX(), e.getY())){
			panel.repaint();
		}
		else{
			return;
		}
		
	}
	
	/**generate a new word**/
    public boolean generateNewWord(int x, int y){
		if(y>300){
			model.getBoard().addWords(new Word(x, y, 120, 14, "Sample",2));
			panel.repaint();
			}
		return true;
	}
	
    /**set a word as selected**/
	public boolean setSelectedWord(Word s){
		Point relative = new Point (anchor);
		
		// no longer in the board since we are moving it around...
		model.setSelected(s);
		originalx = s.getX();
		originaly = s.getY();
			
		// set anchor for smooth moving
		deltaX = relative.x - originalx;
		deltaY = relative.y - originaly;
		return true;
	}
	
	/**set a poem as selected**/
	public boolean setSelectedPoem(Poem p){
		Point relative = new Point(anchor);
		
		model.setSelectedPoem(p);
		originalx = p.getX();
		originaly = p.getY();
		ox = originalx;
		oy = originaly;

		// set anchor for smooth moving
		deltaX = relative.x - originalx;
		deltaY = relative.y - originaly;
		return true;
	}
    
	/**drag mouse**/
	public boolean drag(int x, int y){
		if(model == null){return false;}
		
		Word selectedWord = model.getSelected();
		Poem selectedPoem = model.getSelectedPoem();
	
		//nothing selected
		if(selectedWord == null&&selectedPoem == null&&buildFlag&&!RowFlag){
			return drawSelectionArea(x, y);
		}
		
		//word selected
		if(selectedWord != null){
			selectedWord.setLocation(x-deltaX,y-deltaY);
			return true;
		}
		
		//poem selected
		if(selectedPoem != null){
			ox = selectedPoem.getX();
			oy = selectedPoem.getY();
			selectedPoem.setLocation(x-deltaX,y-deltaY,ox,oy);
			return true;
		}
		
		//row selected
		if(RowFlag){
			shiftSelectedRow(x-deltaX,originaly);
			return true;
		}
		
		return false;
	}
	
	/**release mouse**/
	public boolean release(){
		if (model == null) { return false; }
		
		Word selectedWord = model.getSelected();
		Poem selectedPoem = model.getSelectedPoem();
		
		//nothing selected
		if(selectedWord == null&&selectedPoem == null){
			if(b.getSelectedRow(model.getSelectedArea())!=null);
			model.setSelectedRow(b.getSelectedRow(model.getSelectedArea()));
			model.setSelectedArea(0, 0, 0, 0);
			return true;
		}
		
		//move word or poem;
		if(selectedWord != null){
			this.moveWord(selectedWord);
		}
		else if(selectedPoem != null){
			this.movePoem(selectedPoem);
		}
		
		//release the mouse and repaint
		model.setSelected(null);
		model.setSelectedPoem(null);
		model.setSelectedRow(null);
		selectedWord = null;
		selectedPoem = null;
		buildFlag = false;
		RowFlag = false;
		return true;
	}
	
	/**move a word**/
	public void moveWord(Word w){
		if(this.originaly > 300&&w.getY() > 300){
			this.moveWordinUnprotectedarea(w);
			return;
		}
		
		if(this.originaly > 300&&w.getY() < 300){
			this.changeStatusMove();
			return;
		}
		
		if(this.originaly < 300&&w.getY() < 300){
			this.moveWordinProtectedarea(w);
			return;
		}
		
		if(this.originaly < 300&&w.getY() > 300){
			this.changeStatusMove();
			return;
		}
		
	}
	
	/**move a poem**/
	public void movePoem(Poem p){
		if(this.originaly > 300&&p.getY() > 300){
			return;
		}
		
		if(this.originaly > 300&&p.getY() < 300){
			return;
		}
		
		if(this.originaly < 300&&p.getY() < 300){
			this.movePoeminProtectedarea(p);
			
			return;
		}
		
		if(this.originaly < 300&&p.getY() > 300){
			p.setLocation(originalx,originaly,originalx,originaly);
			return;
		}
	}
	
	/**move a word within unprotected area**/
	public void moveWordinUnprotectedarea(Word w){
		this.makeWordMove();
		return;
	}
	
	/**move a word within protected area**/
	public void moveWordinProtectedarea(Word w){
		//no overlap, just return;
		if(b.checkOverlap(w) == null&&b.checkOverlapWord(w) == null){
			this.makeWordMove();
			return;
		}
		
		//if overlap more than 1 element,set back to original location;
		if(model.getBoard().getOverLapNumber(w)>1){
			w.setLocation(originalx,originaly);
			return;
		}
		
		if(b.checkOverlap(w) != null){
	        this.connectTwoWords(w);
	        return;
		}
		
		if(b.checkOverlapWord(w) != null){
			this.connectPoemandWord(w);
			return;
		}
	}
	
	/**move a poem within protected area**/
	public void movePoeminProtectedarea(Poem p){
		//if overlap, just send back
		if(b.checkOverlapPoem(p)){
			p.setLocation(originalx, originaly,originalx,originaly);
			return;
		}
		
		if(b.getOverlapPoem(p) != null){
			Poem connectPoem = b.getOverlapPoem(p);
			connectTwoPoem(p,connectPoem);
			return;
		}
		this.makePoemMove();
	}
	
	/**check potential overlap with a word**/
	public boolean checkWordPotentialOverlap(Word w){
		Word connectWord = b.checkOverlap(w);
		int type = b.getOverlapType(w,connectWord);
		
		//check potential overlap
		if(type == 1||type == 2||type == 6){
			if(model.getBoard().checkPotentialOverlap(w,connectWord,1)){
				return true;
			}
		}
		else if(type == 3||type == 4||type ==5){
			if(model.getBoard().checkPotentialOverlap(w,connectWord,2)){
				return true;
			}
		}
		
		return false;
	}
	
	/**connect two words**/
    public void connectTwoWords(Word w){
		//check potential overlap
		boolean potentialOverlap = this.checkWordPotentialOverlap(w);
		
		//connect two words
		if(potentialOverlap == false){
		   WordConnectionController connection = new WordConnectionController(model,panel,b.checkOverlap(w),originalx,originaly);
		   connection.connect();
		}
		else{
			w.setLocation(originalx, originaly);
		}
    }
    
    public void connectPoemandWord(Word w){
    	Poem connectPoem = model.getBoard().checkOverlapWord(w);
		int type = model.getBoard().getOverlapPoemWord(connectPoem, w);
		boolean potential =  this.checkPotentialOverlapPoem(type, w, connectPoem);
		
		if(type == 7||type == 8||potential == true){
			w.setLocation(originalx,originaly);
		}
		else{
		WordConnectionController connection = new WordConnectionController(model,panel,connectPoem,originalx,originaly);
		connection.connectPoem(type);
		}
    }
    
   public boolean checkPotentialOverlapPoem(int type, Word w, Poem p){
	      if(type == 1||type == 4||type == 5){
	        	if(model.getBoard().checkPotentialOverlapPoem(w,p,1)){
	        		return true;
	        	}
	        }
	        else if(type == 2||type == 3||type == 6){
	        	if(model.getBoard().checkPotentialOverlapPoem(w,p,2)){
	        		return true;
	        	}
	        }
	   return false;
   }
   
   public void disconnectWord(int x, int y){
	   if(y<300){
   		Poem p = b.findPoem(x,y);
   		Word disconnectWord = null;
   		Row disconnectRow = null;
   		int type = 0;
   		
   		for(Row r:p.getRows()){
   			for(Word w:r.getWords()){
   				if(w.intersection(x,y)){
   				//must be edge word!
   				if(w.getX() == r.getX()){
   					type = 1;
   					disconnectWord = w;
   					disconnectRow = r;
   				}
   				else if(w.getX() == r.getX()+r.getWidth()-w.getWidth()){
   					type = 2;
   					disconnectWord = w;
   					disconnectRow = r;
   				}
   			}
   			}
   		}
   		
   		if(!b.checkDisconnectionAvailability(p, disconnectWord))
   			return;
   		model.setSelectedWordinPoem(disconnectWord);
   		WordDisconnectionController disconnect = new WordDisconnectionController(model,panel,p);
   		disconnect.disconnectEdgeWord(type,disconnectRow);
   	}
   	return;
   }
   
   public void makeWordMove(){
	   Word selectedWord = model.getSelected();
	   if(selectedWord == null){
		   return;
	   }
	   
	   moveWord move = new moveWord(selectedWord,originalx,originaly,selectedWord.getX(),selectedWord.getY());
	   if(move.execute()){
		   model.getMoves().push(move);
		   panel.repaint();
	   }
   }
   
   public void changeStatusMove(){
	   Word selectedWord = model.getSelected();
	   if(selectedWord == null){
		   return;
	   }
	   
	   if(b.checkOverlap(selectedWord) != null||b.checkOverlapWord(selectedWord) != null){
		   selectedWord.setLocation(originalx,originaly);
		   return;
	   }
	   moveProtect move = new moveProtect(selectedWord, b, originalx,originaly,selectedWord.getX(),selectedWord.getY());
	   if(move.execute()){
		   model.getMoves().push(move);
		   panel.repaint();
	   }
   }
   
   public void makePoemMove(){
	   Poem selectedPoem = model.getSelectedPoem();
	   
	   int newx = selectedPoem.getX();
	   int newy = selectedPoem.getY();
	   
	   movePoem move = new movePoem(selectedPoem,originalx,originaly,newx,newy);
	   if(move.execute()){
		   model.getMoves().push(move);
		   panel.repaint();
	   }
   }
   
   public void connectTwoPoem(Poem selectedPoem, Poem connectPoem){
	   WordPoemConnectionController controller = new WordPoemConnectionController(model,panel,originalx,originaly,selectedPoem,connectPoem);
	   controller.connectPoem();
   }
   
   public boolean buildSelectionArea(int ox,int oy){
	   model.setSelectedRow(null);
	   this.originalx = ox;
	   this.originaly = oy;
	   this.buildFlag = true;
	   return true;
   }
   
   public boolean drawSelectionArea(int x, int y){
	   if(x >= originalx&&y >= originaly){
		    int width = Math.abs(x-originalx);
			int height = Math.abs(y-originaly);
			model.setSelectedArea(originalx,originaly,width,height);
			return true;
	   }
	   
	   if(x < originalx&&y < originaly){
		    int width = Math.abs(x-originalx);
			int height = Math.abs(y-originaly);
			model.setSelectedArea(x,y,width,height);
			return true;
	   }
	   
	   if(x > originalx&&y < originaly ){
		    int width = Math.abs(x-originalx);
			int height = Math.abs(y-originaly);
			model.setSelectedArea(originalx,y,width,height);
			return true;
	   }
	   
	   if(x < originalx&&y > originaly){
		   int width = Math.abs(x-originalx);
			int height = Math.abs(y-originaly);
			model.setSelectedArea(x,originaly,width,height);
			return true;
	   }
	   return true;
   }
   
   public boolean shiftSelectedRow(int x,int y){
	   Row r = model.getSelectedRow();
	   int rightLimit = r.getRightShiftLimit();
	   int leftLimit = r.getLeftShiftLimit();
	   
	   int ox = r.getX();
	   int oy = r.getY();
	   
       if(leftLimit == 0||rightLimit == 0){
    	    return false;
       }
	   if(x >= leftLimit && x <= rightLimit ){
	        r.setLocation(x, y, ox, oy);
	        return true;
	    }
	   
	   return false;
   }
   
   public void setRowFlag(Row r){
	   RowFlag = true;
	   Point relative = new Point (anchor);
	   
	   originalx = r.getX();
	   originaly = r.getY();
			
		// set anchor for smooth moving
	   deltaX = relative.x - originalx;
	   deltaY = relative.y - originaly;
  }
}
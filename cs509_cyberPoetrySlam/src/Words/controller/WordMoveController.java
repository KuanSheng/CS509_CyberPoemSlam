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
	
	Point anchor;
	int deltaX;
	int deltaY;
	
	int originalx;
	int originaly;
	
	public WordMoveController(Model model,ApplicationCanvas panel){
		this.model = model;
		this.panel = panel;
	}
	
	public void mousePressed(MouseEvent e) {
		// no board? no behavior!
		if (model == null) { return;}
		Board board = model.getBoard();
		
		if (e.getButton()==MouseEvent.BUTTON3) {
			this.generateNewWord(e.getX(), e.getY());
			return;
		}
		
        if(e.getClickCount() == 2){
        	//must in the protected area
        	if(e.getY()<300){
        		Poem p = board.findPoem(e.getX(),e.getY());
        		Word disconnectWord = null;
        		Row disconnectRow = null;
        		int type = 0;
        		
        		for(Row r:p.getRows()){
        			for(Word w:r.getWords()){
        				if(w.intersection(e.getX(),e.getY())){
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
        		
        		model.setSelectedWordinPoem(disconnectWord);
        		WordDisconnectionController disconnect = new WordDisconnectionController(model,panel,p);
        		disconnect.disconnectEdgeWord(type,disconnectRow);
        		panel.repaint();
        	}
        	return;
        }
        
		anchor = e.getPoint();
		
		// pieces are returned in order of Z coordinate
		Word s = board.findWord(anchor.x, anchor.y);

		if (s != null) {
			setSelectedWord(s);
			return;
		}
	    else{
			Poem p = board.findPoem(anchor.x,anchor.y);
			if(p != null){
				setSelectedPoem(p);
				return;
			}
		}
		
		model.setSelected(null);
		model.setSelectedPoem(null);
	}

	public void mouseReleased(MouseEvent e){
		if(this.release()){
		panel.repaint();}
		else{
			return;
		}
	}
	
	public void mouseDragged(MouseEvent e){
		if(drag(e.getX(), e.getY())){
			panel.repaint();
		}
		else{
			return;
		}
		
	}
	
    public boolean generateNewWord(int x, int y){
		if(y>300){
			model.getBoard().addWords(new Word(x, y, 120, 14, "Sample",2));
			panel.repaint();
			}
		return true;
	}
	
	public boolean setSelectedWord(Word s){
		Point relative = new Point (anchor);
		// no longer in the board since we are moving it around...
		//board.remove(s);
		model.setSelected(s);
		originalx = s.getX();
		originaly = s.getY();
			
		// set anchor for smooth moving
		deltaX = relative.x - originalx;
		deltaY = relative.y - originaly;
		return true;
	}
	
	public boolean setSelectedPoem(Poem p){
		Point relative = new Point(anchor);
		
		model.setSelectedPoem(p);
		originalx = p.getX();
		originaly = p.getY();

		// set anchor for smooth moving
		deltaX = relative.x - originalx;
		deltaY = relative.y - originaly;
		return true;
	}

	public boolean drag(int x, int y){
		if(model == null){return false;}
		Word selected = model.getSelected();
		Poem selectedPoem = model.getSelectedPoem();
		
		if(selected == null){
			if(selectedPoem == null){
				return false;
				}
			else{
				selectedPoem.setLocation(x-deltaX,y-deltaY);
			}
		}
		else if(selected != null){
			selected.setLocation(x-deltaX,y-deltaY);
		}
		
		return true;
	}
	
	public boolean release(){
		if (model == null) { return false; }
		
		Word selected = model.getSelected();
		Poem selectedPoem = model.getSelectedPoem();
		//nothing selected
		if (selected == null) { 
			if(selectedPoem == null){return false;}
			else{
				if(selectedPoem.getY()>300){
					selectedPoem.setLocation(originalx,originaly);
				}
				else{
					if(model.getBoard().checkOverlapPoem(selectedPoem)){
						selectedPoem.setLocation(originalx, originaly);
					}
				}
			}
		}
		
		else {
			if(this.originaly < 300&&selected.getY() > 300){
			//change status;
			model.getBoard().releaseWords(selected);
		}
		
		else if(this.originaly > 300&&selected.getY() < 300){
			//if overlap, set back to original location;
			boolean isOverlap = false;
			if(model.getBoard().checkOverlap(selected)!=null){
				isOverlap = true;
				selected.setLocation(originalx, originaly);
			}
			else{
				if(model.getBoard().checkOverlapWord(selected)!=null){
					isOverlap = true;
					selected.setLocation(originalx, originaly);
				}
			}
			//change status;
			if(isOverlap == false)
			model.getBoard().protectWords(selected);
		}
		
		else if(this.originaly > 300&&selected.getY() > 300){
			
		}
		
		else if(this.originaly < 300&&selected.getY() < 300){
			boolean potential = false;
			boolean potentialWord = false;
			//if overlap,set back to original location;
			if(model.getBoard().getOverLapNumber(selected)>1){
				selected.setLocation(originalx,originaly);
			}
			else if(model.getBoard().checkOverlap(selected)!=null){
				Word connectWord = model.getBoard().checkOverlap(selected);
				int type = model.getBoard().getOverlapType(selected,connectWord);
				//check potential overlap
				if(type == 1||type == 2||type == 6){
					if(model.getBoard().checkPotentialOverlap(selected,connectWord,1)){
						
						potentialWord = true;
					}
				}
				else if(type == 3||type == 4||type ==5){
					if(model.getBoard().checkPotentialOverlap(selected,connectWord,2)){
						System.out.println("error!");
						potentialWord = true;
					}
				}
				//connect two words
				if(potentialWord == false){
				WordConnectionController connection = new WordConnectionController(model,panel,model.getBoard().checkOverlap(selected));
				connection.connect();}
				else{
					selected.setLocation(originalx, originaly);
				}
				
			}
			else{
				if(model.getBoard().checkOverlapWord(selected)!=null){
					Poem connectPoem = model.getBoard().checkOverlapWord(selected);
					int type = model.getBoard().getOverlapPoemWord(connectPoem, selected);
                    if(type == 1||type == 4||type == 5){
                    	if(model.getBoard().checkPotentialOverlapPoem(selected,connectPoem,1)){
                    		potential = true;
                    	}
                    }
                    else if(type == 2||type == 3||type == 6){
                    	if(model.getBoard().checkPotentialOverlapPoem(selected,connectPoem,2)){
                    		potential = true;
                    	}
                    }
                    
                    if(type == 7||type == 8||potential == true){
						selected.setLocation(originalx,originaly);
					}
					else{
					WordConnectionController connection = new WordConnectionController(model,panel,connectPoem);
					connection.connectPoem(type);}
				}
			}
		}
		}
		//release the mouse and repaint
		model.setSelected(null);
		model.setSelectedPoem(null);
		selected = null;
		selectedPoem = null;
		return true;
	}
	
	
}
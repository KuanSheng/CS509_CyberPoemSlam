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
		if (model == null) { return; }
		Board board = model.getBoard();
		
		if (e.getButton()==MouseEvent.BUTTON3) {
			if(e.getY()>300){
			board.addWords(new Word(e.getX(), e.getY(), 120, 14, "Sample",2));
			panel.repaint();
			}
			//System.out.println(e.getX());
			//System.out.println(e.getY());
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
			Point relative = new Point (anchor);
			// no longer in the board since we are moving it around...
			//board.remove(s);
			model.setSelected(s);
			originalx = s.getX();
			originaly = s.getY();
				
			// set anchor for smooth moving
			deltaX = relative.x - originalx;
			deltaY = relative.y - originaly;
			
			// paint will happen once moves. This redraws state to prepare for paint
			//panel.redraw();
			return;
		}
		else{
			Poem p = board.findPoem(anchor.x,anchor.y);
			if(p != null){
				System.out.println("woca");
				Point relative = new Point(anchor);
				
				model.setSelectedPoem(p);
				originalx = p.getX();
				originaly = p.getY();

				// set anchor for smooth moving
				deltaX = relative.x - originalx;
				deltaY = relative.y - originaly;
				
				return;
			}
		}
		
		model.setSelected(null);
		model.setSelectedPoem(null);
	}

	
	public void mouseReleased(MouseEvent e){
		//no model
		if (model == null) { return; }
		
		Word selected = model.getSelected();
		Poem selectedPoem = model.getSelectedPoem();
		//nothing selected
		if (selected == null) { 
			if(selectedPoem == null){return;}
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
			//if overlap,set back to original location;
			if(model.getBoard().checkOverlap(selected)!=null){
				//connect two words
				WordConnectionController connection = new WordConnectionController(model,panel,model.getBoard().checkOverlap(selected));
				connection.connect();
			}
			else{
				if(model.getBoard().checkOverlapWord(selected)!=null){
					Poem connectPoem = model.getBoard().checkOverlapWord(selected);
					int type = model.getBoard().getOverlapPoemWord(connectPoem, selected);
					System.out.println(type);
					if(type == 7||type == 8){
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
		panel.repaint();
	}
	
	public void mouseDragged(MouseEvent e){
		if(model == null){return;}
		Word selected = model.getSelected();
		Poem selectedPoem = model.getSelectedPoem();
		
		if(selected == null){
			if(selectedPoem == null){
				return;
				}
			else{
				selectedPoem.setLocation(e.getX()-deltaX,e.getY()-deltaY);
			}
		}
		else if(selected != null){
			selected.setLocation(e.getX()-deltaX,e.getY()-deltaY);
		}
		
		panel.repaint();
	}
	
}

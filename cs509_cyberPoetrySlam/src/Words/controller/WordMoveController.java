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
		
		if (model == null) { 
		return; }
		Board board = model.getBoard();
		
		if (e.getButton()==MouseEvent.BUTTON3) {
			board.addWords(new Word(e.getX(), e.getY(), 200, 14, "Sample",2));
			//panel.redraw();
			panel.repaint();
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
		
		model.setSelected(null);
	}
	
	public void mouseReleased(MouseEvent e){
		//no model
		if (model == null) { return; }
		Word selected = model.getSelected();
		//nothing selected
		if (selected == null) { return; }
		
		if(this.originaly < 300&&selected.getY() > 300){
			//change status;
			model.getBoard().releaseWords(selected);
		}
		
		else if(this.originaly > 300&&selected.getY() < 300){
			//change status;
			model.getBoard().protectWords(selected);
			//if overlap, set back to original location;
			for(Iterator<Word> iter = model.getBoard().iterator();iter.hasNext();){
				if(selected.overlap(iter.next())){
					selected.setLocation(originalx, originaly);
					break;
					}
			}
		}
		
		else if(this.originaly > 300&&selected.getY() > 300){
			
		}
		
		else if(this.originaly < 300&&selected.getY() < 300){
			//if overlap,set back to original location;
			for(Iterator<Word> iter = model.getBoard().iterator();iter.hasNext();){
				if(selected.overlap(iter.next())){
					selected.setLocation(originalx, originaly);
					break;
					}
			}
		}
		
		//release the mouse and repaint
		selected = null;
		panel.repaint();
	}
	
	public void mouseDragged(MouseEvent e){
		if(model == null){return;}
		Word selected = model.getSelected();
		
		if(selected == null){return;}
		selected.setLocation(e.getX()-deltaX,e.getY()-deltaY);
		panel.paintWord(selected);
		
		panel.repaint();
	}
	
}

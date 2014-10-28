package Words.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

import java.util.*;

import Words.controller.WordConnectionController;
import Words.controller.WordMoveController;
import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Word;

public class ApplicationCanvas extends Canvas{
	Board board;
	Model model;
	Image offscreenImage;
	Graphics offscreengraphics;
	Graphics canvasGraphics;
	
	//Constructor
	public ApplicationCanvas(Model m){
		super();
		this.model = m;
		initialize();
	}
	
	//initialize frame attributes
	public void initialize(){
		if(model == null)
	        System.out.println("fuck too!!(canvas)");
		setSize(650,490);
		WordMoveController controller = new WordMoveController(model,this);
		//WordConnectionController connectionController = new WordConnectionController(model,this);
		this.board = model.getBoard();
		this.addMouseListener(controller);
		this.addMouseMotionListener(controller);
	}
	
	public void paint(Graphics g){
		g.clearRect(0,0,getWidth(),300);
		g.setColor(Color.ORANGE);
		g.fillRect(0,0,getWidth(), 300);
		g.drawLine(0,300, 650, 300);
		
		for(Word w : board){
			g.clearRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
			g.setColor(Color.gray);
			g.fillRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
			g.setColor(Color.black);
			g.drawString(w.getValue(),w.getX()+w.getWidth()/2,w.getY()+w.getHeight());
		}
	}
	
	public void paintWord(Word word){}
	//need poem model design first
	public void paintRow(){}
	public void paintPoem(){
		//for(Iterator itr = board.poemIterator();itr.hasNext());
	}
	public void paintBackgroud(){
		
	}
}

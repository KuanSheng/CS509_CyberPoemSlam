package Words.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

import java.util.*;

//import Words.controller.WordConnectionController;
import Words.controller.WordMoveController;
import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Row;
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
        WordMoveController controller = new WordMoveController(model, this);
        //WordConnectionController connectionController = new WordConnectionController(model,this);
		this.board = model.getBoard();
		this.addMouseListener(controller);
		this.addMouseMotionListener(controller);
	}
	
	public void paint(Graphics g){
		paintBackground(g);
		paintWord(g);
		paintPoem(g);
		paintDisconnectWord(g);
		paintSelected(g);
		paintSelectedPoem(g);
	}
	
	public void paintBackground(Graphics g){
		g.clearRect(0,0,getWidth(),300);
		g.setColor(Color.ORANGE);
		g.fillRect(0,0,getWidth(), 300);
		g.drawLine(0,300, 650, 300);
	}
	
	public void paintWord(Graphics g){
		for(Word w : board.getWords()){
			g.clearRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
			g.setColor(Color.gray);
			g.fillRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
			g.setColor(Color.black);
			g.drawString(w.getValue(),w.getX()+w.getWidth()/2,w.getY()+w.getHeight());
			
		}
	}
	
	public void paintWord(Word word){}
	//need poem model design first
	public void paintRow(Row r,Graphics g){
		g.clearRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
		g.setColor(Color.blue);
		g.fillRect(r.getX(),r.getY(),r.getWidth(),r.getHeight());
		g.setColor(Color.white);
		g.drawLine(r.getX(), r.getY(), r.getX(), r.getY()+r.getHeight());
		g.drawLine(r.getX(), r.getY(), r.getX()+r.getWidth(), r.getY());
		g.drawLine(r.getX(), r.getY()+r.getHeight(),r.getX()+r.getWidth(),r.getY()+r.getHeight());
		for(Iterator<Word> wordItr = r.iterator();wordItr.hasNext();){
			Word word = wordItr.next();
			System.out.println(word.getValue()+":"+word.getWidth());
			System.out.println(word.getValue()+":"+word.getX());
			g.setColor(Color.white);
			g.drawString(word.getValue(), word.getX()+word.getWidth()/2, word.getY()+r.getHeight());
			g.setColor(Color.white);
			g.drawLine(word.getX()+word.getWidth(),word.getY(),word.getX()+word.getWidth(),word.getY()+word.getHeight());
			}
	}
	
	public void paintPoem(Graphics g){
		for(Iterator<Poem> itr = board.poemIterator();itr.hasNext();){
			Poem paintPoem = itr.next();
			for(Row r: paintPoem.getRows()){
				paintRow(r,g);
			}
		}
	}
	
	public void paintDisconnectWord(Graphics g){
		Word w = model.getSelectedWordinPoem();
		if(w != null){
			g.clearRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
			g.setColor(Color.GREEN);
			g.fillRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
			g.drawString(w.getValue(), w.getX()+w.getWidth()/2, w.getY()+w.getHeight());
		}
	}
	
	public void paintSelected(Graphics g){
		Word selected = model.getSelected();
		if(selected  == null){
			return;
		}
		
		g.clearRect(selected.getX(),selected.getY(), selected.getWidth(), selected.getHeight());
		g.setColor(Color.green);
		g.fillRect(selected.getX(),selected.getY(), selected.getWidth(),selected.getHeight());
		g.setColor(Color.white);
		g.drawString(selected.getValue(), selected.getX()+selected.getWidth()/2, selected.getY()+selected.getHeight());
	}
	
	public void paintSelectedPoem(Graphics g){
		Poem selectedPoem = model.getSelectedPoem();
		if(selectedPoem  == null){
			return;
		}
		
		for(Row r:selectedPoem.getRows()){
			for(Word w:r.getWords()){
				g.clearRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
				g.setColor(Color.green);
				g.fillRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
				g.setColor(Color.white);
				g.drawString(w.getValue(),w.getX()+w.getWidth()/2,w.getY()+w.getHeight());
			}
		}
	}

}

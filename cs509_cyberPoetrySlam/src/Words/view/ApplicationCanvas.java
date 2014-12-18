package Words.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.util.*;

//import Words.controller.SwapRequestController;
//import Words.controller.WordConnectionController;
import Words.controller.WordMoveController;
import Words.model.Area;
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

    private static final Color HIGHLIGH_COLOR = Color.red;
	
	//Constructor
	public ApplicationCanvas(Model m){
		super();
		this.model = m;
		initialize();
	}
	
	//initialize frame attributes
	public void initialize(){
		if(model == null)
	        System.out.println("error");
		setSize(650,490);
        WordMoveController controller = new WordMoveController(model, this);
		this.board = model.getBoard();
		this.addMouseListener(controller);
		this.addMouseMotionListener(controller);
	}
	
	public void paint(Graphics g){
		if( offscreenImage == null){
			offscreenImage = this.createImage(this.getWidth(), this.getHeight());
			offscreengraphics = offscreenImage.getGraphics();
		}
		offscreengraphics.clearRect(0,0, this.getWidth(), this.getHeight());
		paintBackground(offscreengraphics);
		paintWord(offscreengraphics);
		paintPoem(offscreengraphics);
		paintDisconnectWord(offscreengraphics);
		
		if(model.getSelectedRow() != null){
		paintSelectedRow(offscreengraphics);
		}
		
		if(model.getSelected() != null){
		paintSelected(offscreengraphics);
		}
		
		if(model.getSelectedPoem() != null){
		paintSelectedPoem(offscreengraphics);
		}
		
		if(model.getSubmittedPoem() != null){
		paintSubmitPoem(offscreengraphics);
		}
		paintSelectedArea(offscreengraphics);

        //added by JUN to paint highlighted word (selected by clicking in word table)
        if(model.getHighlightWord() != null){
            paintHighlightWord(offscreengraphics);
        }
        
        g.drawImage(offscreenImage,0,0,this);
	}
	
	@Override
	public void update(Graphics g){
		paint(g);
	}
	/**paint backgroud**/
	public void paintBackground(Graphics g){
//        System.out.println("paintBackground.ApplicationCanvas.java");
		g.clearRect(0,0,getWidth(),300);
//        g.clearRect(0,0,getWidth(),getHeight()*2/3);
		g.setColor(Color.ORANGE);
		g.fillRect(0,0,getWidth(), 300);
//        g.fillRect(0,0,getWidth(), getHeight()*2/3);
		g.drawLine(0,300, 650, 300);
	}
	/**paint all words**/
	public void paintWord(Graphics g){
		for(Word w : board.getWords()){
//            System.out.println("painting word: " + w + "at x-" + w.getX() + " y-" + w.getY() + " :paintWord.ApplicationCanvas");
            g.clearRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
			g.setColor(Color.gray);
			g.fillRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
			g.setColor(Color.black);
			g.drawString(w.getValue(),w.getX()+w.getWidth()/2,w.getY()+w.getHeight());
			
		}
	}
	
	
	/**
	 * Ruizhu add for BrokerManager
	 */
//	public void paintSwapAddWord(int x, int y, Graphics g, Word w){
//		    g.clearRect(x, y, w.getWidth(), w.getHeight());
//			g.setColor(Color.gray);
//			g.fillRect(x, y, w.getWidth(), w.getHeight());
//			g.setColor(Color.black);
//			g.drawString(w.getValue(), x+w.getWidth()/2, y+w.getHeight());
//	}
	
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
	
	public void paintSelectedArea(Graphics g){
		Area a = model.getSelectedArea();
        if(a == null) return; //added by JUN to solve null pointer exception
		g.drawRect(a.getX(),a.getY(),a.getWidth(),a.getHeight());
		g.setColor(Color.GREEN);
		g.fillRect(a.getX(), a.getY(), a.getWidth(), a.getHeight());
	}
	
	public void paintSelectedRow(Graphics g){
		Row r = model.getSelectedRow();
		g.clearRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
		g.setColor(Color.red);
		g.fillRect(r.getX(),r.getY(),r.getWidth(),r.getHeight());
		g.setColor(Color.white);
		g.drawLine(r.getX(), r.getY(), r.getX(), r.getY()+r.getHeight());
		g.drawLine(r.getX(), r.getY(), r.getX()+r.getWidth(), r.getY());
		g.drawLine(r.getX(), r.getY()+r.getHeight(),r.getX()+r.getWidth(),r.getY()+r.getHeight());
		for(Iterator<Word> wordItr = r.iterator();wordItr.hasNext();){
			Word word = wordItr.next();
			g.setColor(Color.white);
			g.drawString(word.getValue(), word.getX()+word.getWidth()/2, word.getY()+r.getHeight());
			g.setColor(Color.white);
			g.drawLine(word.getX()+word.getWidth(),word.getY(),word.getX()+word.getWidth(),word.getY()+word.getHeight());
			}
	}
	
	public void paintSubmitPoem(Graphics g){
		Poem p = model.getSubmittedPoem();
		for(Row r:p.getRows()){
		g.clearRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
		g.setColor(Color.black);
		g.fillRect(r.getX(),r.getY(),r.getWidth(),r.getHeight());
		g.setColor(Color.white);
		g.drawLine(r.getX(), r.getY(), r.getX(), r.getY()+r.getHeight());
		g.drawLine(r.getX(), r.getY(), r.getX()+r.getWidth(), r.getY());
		g.drawLine(r.getX(), r.getY()+r.getHeight(),r.getX()+r.getWidth(),r.getY()+r.getHeight());
		for(Iterator<Word> wordItr = r.iterator();wordItr.hasNext();){
			Word word = wordItr.next();
			g.setColor(Color.white);
			g.drawString(word.getValue(), word.getX()+word.getWidth()/2, word.getY()+r.getHeight());
			g.setColor(Color.white);
			g.drawLine(word.getX()+word.getWidth(),word.getY(),word.getX()+word.getWidth(),word.getY()+word.getHeight());
			}
	    }
    }

    /**
     * JUN added to paint highlighted word, selected by clicking in word table
     * @param g
     */
    public void paintHighlightWord(Graphics g){
        Word w = model.getHighlightWord();
        g.clearRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
        g.setColor(HIGHLIGH_COLOR);
        g.fillRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
        g.setColor(Color.black);
        g.drawString(w.getValue(),w.getX()+w.getWidth()/2,w.getY()+w.getHeight());
    }

}

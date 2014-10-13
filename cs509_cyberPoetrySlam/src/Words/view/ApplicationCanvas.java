package Words.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

import Words.controller.WordMoveController;
import Words.model.Board;
import Words.model.Model;
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
		setSize(650,490);
		WordMoveController controller = new WordMoveController(model,this);
		addMouseListener(controller);
		addMouseMotionListener(controller);
	}
	
	public void paint(Graphics g){}
	public void paintWord(Word word){}
	//need poem model design first
	public void paintPoem(){}
	public void paintBackgroud(){}
	
}

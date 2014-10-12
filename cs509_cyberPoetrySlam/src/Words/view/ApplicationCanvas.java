package Words.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

import Words.model.Board;
import Words.model.Model;

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
	}
	
}

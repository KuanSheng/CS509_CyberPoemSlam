package Words.controller;

import java.awt.Point;
import java.awt.event.*;

import Words.model.*;
import Words.view.*;

public class WordController extends MouseAdapter{
	final Model model;
	final ApplicationCanvas panel;
	
	Point anchor;
	int deltaX;
	int deltaY;
	
	int originalx;
	int originaly;
	
	public WordController(Model model,ApplicationCanvas panel){
		this.model = model;
		this.panel = panel;
	}
	
	
}

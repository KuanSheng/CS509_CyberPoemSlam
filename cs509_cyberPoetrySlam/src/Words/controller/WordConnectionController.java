package Words.controller;

import java.awt.event.MouseAdapter;

import Words.model.Model;
import Words.view.ApplicationCanvas;

public class WordConnectionController extends MouseAdapter {
	final Model model;
	final ApplicationCanvas panel;
	
	public WordConnectionController(Model m,ApplicationCanvas p){
		this.model = m;
		this.panel = p;
	}
}

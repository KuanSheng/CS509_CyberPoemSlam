package Words.controller;

import Words.model.*;

import Words.model.Model;
import Words.view.ApplicationCanvas;

public class WordConnectionController{
	final Model model;
	final ApplicationCanvas panel;
	final Board b;
	Word connectWord;
	Poem connectPoem;
	
	public WordConnectionController(Model m,ApplicationCanvas p,Word w){
		this.model = m;
		this.panel = p;
		this.connectWord = w;
		b = model.getBoard();
	}
	
	public WordConnectionController(Model m, ApplicationCanvas a, Poem p){
		this.model = m;
		this.panel = a;
		this.connectPoem = p;
		b = model.getBoard();
	}
	
	public void connect(){
		Word selectedWord = this.model.getSelected();
		int type = b.getOverlapType(selectedWord, this.connectWord);
		Poem newPoem;
		
		switch(type){
		case 3:
			newPoem = new Poem(this.connectWord,selectedWord,1);
		    break;
		
		case 4:
			newPoem = new Poem(this.connectWord,selectedWord,1);
			break;
		case 2:
			newPoem = new Poem(selectedWord,this.connectWord,2);
			break;
		case 1:
			newPoem = new Poem(selectedWord,this.connectWord,2);
			break;
		case 5:
			newPoem = new Poem(this.connectWord,selectedWord,1);
			break;
		case 6:
			newPoem = new Poem(selectedWord,this.connectWord,2);
			break;
		default:
			newPoem = new Poem(this.connectWord,selectedWord,1);
			break;
		}
		
		b.addPoems(newPoem);
		b.getWords().remove(selectedWord);
		b.getWords().remove(this.connectWord);
		panel.repaint();
		}
	
	public void connectPoem(int connectionType){
		Word selectedWord = model.getSelected();
		if(connectionType == 1||connectionType == 4||connectionType == 5){
		Row r = connectPoem.getOverlapRow(selectedWord);
		
		r.addWord(selectedWord);
		selectedWord.setLocation(r.getX()-selectedWord.getWidth(), r.getY());
		r.setLocation(selectedWord.getX(),selectedWord.getY());
		connectPoem.setLocation(r.getX(),r.getY());
		b.getWords().remove(selectedWord);
		panel.repaint();
		}
		else if(connectionType == 2||connectionType == 3||connectionType == 6){
			Row r = connectPoem.getOverlapRow(selectedWord);
			
			r.addWord(selectedWord);
			selectedWord.setLocation(r.getX()+r.getWidth()-selectedWord.getWidth(),r.getY());
			b.getWords().remove(selectedWord);
			panel.repaint();
		}
	}
}

package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Row;
import Words.model.Word;
import Words.view.Application;
import Words.view.ApplicationCanvas;

public class WordDisconnectionControllerTest {
	 Model model;
		ApplicationCanvas panel;
		Board b0;
		
		Poem disconnectPoem;
		WordDisconnectionController CC1;
    
	@Before
	public void setUp() throws Exception {
		b0 = new Board();
		model= new Model(b0);
		panel = new ApplicationCanvas(model);
		CC1=new WordDisconnectionController(model, panel, disconnectPoem);
		
		
		assertEquals(model, CC1.model);
		assertEquals(panel, CC1.panel);
		assertEquals(disconnectPoem, CC1.disConnectPoem);
	}


	@Test
	public void testDisconnectEdgeWord() {
		Word w1= new Word(50, 55, 13,20,"new",1);
		Word w2=new Word(60,55,13,20,"ha",1);
		
		disconnectPoem = new Poem(w1,w2,1);
		model.setSelectedWordinPoem(w1);
		
		Row row=new Row(w1,w2,1);
		assertEquals(2,row.getWordNumber());
		CC1=new WordDisconnectionController(model, panel, disconnectPoem);
		CC1.disconnectEdgeWord(1,row);
	//	CC1.disconnectEdgeWord(2,row);
		
	
		Poem disconnectPoem1 = new Poem(w1,w2,1);
		model.setSelectedWordinPoem(w1);
		Word w3 = new Word(45,55,13,20,"new",1);
		Row row1=new Row(w1,w2,1);
		row1.addWord(w3);
		assertEquals(3,row1.getWordNumber());
		CC1=new WordDisconnectionController(model, panel, disconnectPoem1);
		CC1.disconnectEdgeWord(1,row1);
		
		Poem disconnectPoem2 = new Poem(w1,w2,1);
		model.setSelectedWordinPoem(w1);
		Row row2=new Row(w1,w2,1);
		assertEquals(2,row1.getWordNumber());
		CC1=new WordDisconnectionController(model, panel, disconnectPoem2);
		CC1.disconnectEdgeWord(2,row2);
		
		Poem disconnectPoem3 = new Poem(w1,w2,1);
		model.setSelectedWordinPoem(w1);
		Row row3=new Row(w1,w2,1);
		row1.addWord(w3);
	//	assertEquals(3,row3.getWordNumber());
		CC1=new WordDisconnectionController(model, panel, disconnectPoem3);
		CC1.disconnectEdgeWord(2,row3);
		
		
	}

}

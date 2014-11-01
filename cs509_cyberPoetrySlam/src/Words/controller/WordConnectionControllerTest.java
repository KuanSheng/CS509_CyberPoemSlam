package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Word;
import Words.view.ApplicationCanvas;

public class WordConnectionControllerTest {
	
    Model model;
	ApplicationCanvas panel;
	Board b0;
	Word connectWord;
	Poem connectPoem;
	WordConnectionController CC1;
	WordConnectionController CC2;
	

	@Before
	public void setUp() throws Exception {
		
		b0 = new Board();
		model= new Model(b0);
		panel = new ApplicationCanvas(model);
		CC1=new WordConnectionController(model, panel, connectWord);
		CC2=new WordConnectionController(model, panel, connectPoem );
		
		assertEquals(model, CC1.model);
		assertEquals(panel, CC1.panel);
		assertEquals(connectWord, CC1.connectWord);
		assertEquals(connectPoem, CC1.connectPoem);
	}

	@Test
	public void testWordConnectionControllerModelApplicationCanvasWord() {
		Word w1 = new Word(10, 20, 20,20, "Test",1);
		model.setSelected(w1);
		assertEquals(w1,model.getSelected());
	    assertEquals(10, model.getSelected().getX());
		connectWord=new Word (25,30,10,20,"connectW",1);
	
		
    	connectWord=new Word (40,20,60,10,"connectW",1);
    	CC1=new WordConnectionController(model, panel, connectWord);
    	assertEquals(40, CC1.connectWord.getX());
    	assertEquals(20, CC1.connectWord.getY());
    	assertEquals(60, CC1.connectWord.getWidth());
    	
    	CC1.connect();
        
    	connectWord=new Word (5,39,6, 20,"connectW",1);
    	CC1=new WordConnectionController(model, panel, connectWord);
    	assertEquals(5, CC1.connectWord.getX());
    	assertEquals(6, CC1.connectWord.getWidth());
    	CC1.connect();
		
		
	}

	@Test
	public void testWordConnectionControllerModelApplicationCanvasPoem() {
	
	}

	@Test
	public void testConnect() {
		
	}

	@Test
	public void testConnectPoem() {
		
	}

}

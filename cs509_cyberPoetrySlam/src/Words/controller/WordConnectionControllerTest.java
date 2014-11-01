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
		CC1=new WordConnectionController(model, panel, connectWord);
		CC1.connect();
	
		Word w2 = new Word(40, 50, 40,20,"Test",1 );
		model.setSelected(w2);
		assertEquals(w2,model.getSelected());
	    assertEquals(40, model.getSelected().getX());
	    connectWord=new Word(40,40,50,20,"New",1);
	    CC1=new WordConnectionController(model, panel, connectWord);
	    CC1.connect();
	    
	    Word w3 =new Word(55, 60, 13, 20,"test",1);
	    model.setSelected(w3);
	    connectWord=new Word(50, 50 ,9,20,"Test",1) ;
	    CC1=new WordConnectionController(model, panel, connectWord);
	    CC1.connect();
	    
	    Word w4 = new Word(150, 200, 30, 20,"Test",1);
	    model.setSelected(w4);
	    connectWord=new Word(140, 210, 30,20, "Test",1 );
	    CC1=new WordConnectionController(model, panel, connectWord);
	    CC1.connect();

	    Word w5= new Word(200,200, 40,20,"new",1);
	    model.setSelected(w5);
	    connectWord=new Word(170,200 ,40,20,"new",1);
	    CC1=new WordConnectionController(model, panel, connectWord);
	    CC1.connect();
	    
	    Word w6 = new Word(250,100, 50,20,"Test",1);
	    model.setSelected(w6);
	    connectWord=new Word(280,100,50,20,"test",1);
	    CC1=new WordConnectionController(model, panel, connectWord);
	    CC1.connect();
	    
	    Word w7 = new Word(1,2,10,20,"new",1);
	    model.setSelected(w7);
	    connectWord=new Word(200,200,10,20,"new",1);
	    CC1=new WordConnectionController(model, panel, connectWord);
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

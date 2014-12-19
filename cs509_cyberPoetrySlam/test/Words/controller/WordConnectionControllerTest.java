package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Row;
import Words.model.Word;
import Words.view.ApplicationCanvas;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class WordConnectionControllerTest {
	
    Model model;
	ApplicationCanvas panel;
	Board b0;
	int oldXW;
	int oldYW;
	int oldXP;
	int oldYP;
	WordConnectionController CC1;
	WordConnectionController CC2;
	

	@Before
	public void setUp() throws Exception {
		
		b0 = new Board();
		model= new Model(b0);
		panel = new ApplicationCanvas(model);
		Word connectWord_1 = new Word(40, 490, 10, 20, "my", 1);
		oldXW = 50;
		oldYW = 200;
		oldXP = 100;
		oldYP = 100;
		
		Word word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		Word word2 = new Word(80, 530, 30 ,14, "cat", 2);
		Row row2 = new Row(word1,word2, 2);
		
		Word word3 = new Word(100, 550, 30,14,"dog", 2);
		Word word4 = new Word(100, 490, 50,14,  "white", 0);
		Row row3 = new Row(word3, word4, 1);
		
		Poem poem1 = new Poem (100,200);
		Poem poem2 = new Poem(word1, word2, 2);
		Poem poem3 = new Poem(word3, word4, 1);
		Poem poem4 = new Poem(poem2, poem3, 1);

		
		CC1=new WordConnectionController(model, panel, connectWord_1, oldXW, oldYW);
		CC2=new WordConnectionController(model, panel, poem4, oldXP, oldYP);
		
		assertEquals(model, CC1.model);
		assertEquals(panel, CC1.panel);
		assertEquals(connectWord_1, CC1.connectWord);
		assertEquals(poem4, CC2.connectPoem);
	}
	
	@Test
	public void testConnectPoem() {
		Word word5 = new Word(60,476,40,14,"new",1);
		model.setSelected(word5);
		CC2.connectPoem(1);
	    CC2.panel.repaint();
	    assertEquals(0,b0.getPoems().size());
			
	}

	@Test
	public void testConnectPoemS2() {
		Word word5 = new Word(60,476,40,14,"new",1);
		model.setSelected(word5);
		CC2.connectPoem(2);
		 assertEquals(0,b0.getPoems().size());
			
	}

	@Test
	public void testConnect() {

		Word word1 = new Word(45,480, 10,20, "Happy", 1);
		model.setSelected(word1);
		CC1.connect();
		assertEquals(1,b0.getPoems().size());
		
		
		Word word2 = new Word(35,500, 10,20,"Happy",1);
		model.setSelected(word2);
		CC1.connect();
		assertEquals(2,b0.getPoems().size());
		
		Word word3 = new Word(35, 480, 10, 20, "Happy", 1);
		model.setSelected(word3);
		CC1.connect();
		assertEquals(3,b0.getPoems().size());
		
		Word word4 = new Word(45, 500, 10,20, "Happy", 1);
		model.setSelected(word4);
		CC1.connect();
		assertEquals(4,b0.getPoems().size());
		
		Word word5 = new Word(35,490, 10, 20, "Happy", 1);
		model.setSelected(word5);
		CC1.connect();
		assertEquals(5,b0.getPoems().size());
		
		Word word6 = new Word(35,490, 30, 20, "Happy", 1);
		model.setSelected(word6);
		CC1.connect();
		assertEquals(6,b0.getPoems().size());
		
		Word word7 = new Word(70,490, 10, 20, "Happy",1);
		model.setSelected(word7);
		CC1.connect();
		CC1.panel.paintWord(word7);
		assertEquals(7,b0.getPoems().size());
		
	}



	
}

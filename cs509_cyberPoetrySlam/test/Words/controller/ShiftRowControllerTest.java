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
public class ShiftRowControllerTest {
	
	Board b;
	Model model;
	ApplicationCanvas panel;

	Row row, row1, row2, row3;
	Word word1,word2, word3, word4;
	Poem poem2, poem3, poem4;
	ShiftRowController SC1, SC2;
	
	
	@Before
	public void setUp() throws Exception {
		b= new Board();
		model = new Model(b);
		panel = new ApplicationCanvas(model);
		
		word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		word2 = new Word(80, 530, 30 ,14, "cat", 2);
		row2 = new Row(word1,word2, 2);
		
		word3 = new Word(100, 550, 30,14,"dog", 2);
		word4 = new Word(100, 490, 50,14,  "white", 0);
		row3 = new Row(word3, word4, 1);
		
		poem2 = new Poem(word1, word2, 2);
		poem3 = new Poem(word3, word4, 1); // x 70 , y 490
		poem4 = new Poem(poem2, poem3, 1);
		
		row1 = poem4.getFirstRow();
		row = poem4.getLastRow();
		SC1 = new ShiftRowController(model,panel,poem4, row1, 50  ,476  ,70,476);
		SC2 = new ShiftRowController(model,panel,poem4, row, 90  ,490  ,70,476);
		
		
	}



	@Test
	public void testShift() {
		SC1.shift();
		assertEquals(row1.getX(),50);
		assertEquals(row1.getY(),476);
		SC2.shift();
		assertEquals(row.getX(),90);
		assertEquals(row.getY(),490);
	}

}

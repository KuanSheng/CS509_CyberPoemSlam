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
public class ShiftRowMoveTest {

	Row row, row1, row2, row3;
	Word word1,word2, word3, word4;
	Poem poem2, poem3, poem4;
	ShiftRowMove SRM;

	@Before
	public void setUp() throws Exception {

		word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		word2 = new Word(80, 600, 30 ,14, "cat", 2);
		row2 = new Row(word1,word2, 2);
		
		word3 = new Word(60, 610, 30,14,"dog", 2);
		word4 = new Word(70, 610, 50,14,  "white", 0);
		row3 = new Row(word3, word4, 1);
		
		poem2 = new Poem(word1, word2, 2);
		poem3 = new Poem(word3, word4, 1); // x 70 , y 490
		poem4 = new Poem(poem2, poem3, 1);
		
		row1 = poem4.getFirstRow();
		row = poem4.getLastRow();
	}

	@Test
	public void testExecute() {
		SRM = new ShiftRowMove(poem4,row1,30,600,60,600);
		SRM.execute();
		assertEquals(row1.getX(),30);
		assertEquals(row1.getY(),600);
		SRM.undo();
		assertEquals(row1.getX(),60);
		assertEquals(row1.getY(),600);
		SRM.redo();
		assertEquals(row1.getX(),30);
		assertEquals(row1.getY(),600);
		
		SRM = new ShiftRowMove(poem4,row,20,610,70,600);
		SRM.execute();
		assertEquals(row.getX(),20);
		assertEquals(row.getY(),610);
		SRM.undo();
		assertEquals(row.getX(),70);
		assertEquals(row.getY(),600);
		SRM.redo();
		assertEquals(row.getX(),20);
		assertEquals(row.getY(),610);
		
	}


}

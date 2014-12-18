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
public class DisconnectionPoemControllerTest {
	Model model;
	ApplicationCanvas panel;
	Poem disconnectPoem;
	Row disconnectRow;
	Board board;
	Word word1, word2,word3,word4;
	Poem poem1, poem2 , poem3, poem4, poem5;	
	DisconnectionPoemController DPC;

	@Before
	public void setUp() throws Exception {
		word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		word2 = new Word(80, 530, 30 ,14, "cat", 2);
		word3 = new Word(100, 550, 30,14,"dog", 2);
		word4 = new Word(100, 490, 50,14,  "white", 0);
		poem2 = new Poem(word1, word2, 2);
		poem3 = new Poem(word3, word4, 1);
		poem1 = new Poem(poem2, poem3,1);
	}


	@Test
	public void testDisconnectRow() {
		board = new Board();
		board.getPoems().add(poem1);
		model = new Model(board);
		model.setSelectedRow(poem1.getFirstRow());
		model.setSelectedPoem(poem1);
		panel = new ApplicationCanvas(model);
		DPC = new DisconnectionPoemController(model, panel);
		DPC.disconnectRow();
		assertEquals(null, poem1.getRowByWord(word1));
		assertEquals(poem1.getLastRow(), poem1.getRowByWord(word3));
	}
	@Test
	public void testDisconnectMiddleRow(){

		word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		word2 = new Word(80, 530, 30 ,14, "cat", 2);
		word3 = new Word(100, 550, 30,14,"dog", 2);
		word4 = new Word(100, 490, 50,14,  "white", 0);
		Word word5 = new Word(70, 507, 40, 14, "lovely", 0 );
		Word word6 = new Word(100, 507, 30 ,14, "cat", 2);
		
		poem2 = new Poem(word1, word2, 2);
		poem3 = new Poem(word3, word4, 1);
		poem4 = new Poem(word5, word6, 2);
		poem5 = new Poem(poem2, poem3, 1);
		poem1 = new Poem(poem4, poem5,1);
		
		board = new Board();
		board.getPoems().add(poem1);
		model = new Model(board);
		model.setSelectedRow(poem1.getRowByWord(word1));
		model.setSelectedPoem(poem1);
		panel = new ApplicationCanvas(model);
		DPC = new DisconnectionPoemController(model, panel);
		DPC.disconnectRow();
		
		
	}
	
	@Test
	public void testDisconnectlastRow(){
		board = new Board();
		board.getPoems().add(poem1);
		model = new Model(board);
		model.setSelectedRow(poem1.getLastRow());
		model.setSelectedPoem(poem1);
		panel = new ApplicationCanvas(model);
		DPC = new DisconnectionPoemController(model, panel);
		DPC.disconnectRow();
		assertEquals(null, poem1.getRowByWord(word3));
		assertEquals(poem1.getFirstRow(), poem1.getRowByWord(word1));
	}

	
}

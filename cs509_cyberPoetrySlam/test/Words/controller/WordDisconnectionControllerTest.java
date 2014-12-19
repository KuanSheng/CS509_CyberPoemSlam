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
/**
 * Created by Ruizhu on 12/17/14.
 */
public class WordDisconnectionControllerTest {
	 Model model;
		ApplicationCanvas panel;
		Board b0;
		
		Poem disconnectPoem;
		WordDisconnectionController CC1;
		
		Word word1, word2,word3,word4;
		Poem poem1,poem2,poem3, poem4, poem5;
    
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
		assertEquals(row.getWordNumber(),1);
	
		Poem disconnectPoem1 = new Poem(w1,w2,1);
		model.setSelectedWordinPoem(w1);
		Word w3 = new Word(45,55,13,20,"new",1);
		Row row1=new Row(w1,w2,1);
		row1.addWord(w3);
		assertEquals(3,row1.getWordNumber());
		CC1=new WordDisconnectionController(model, panel, disconnectPoem1);
		CC1.disconnectEdgeWord(1,row1);
		assertEquals(disconnectPoem1.getFirstRow().getWordNumber(),2);
		assertEquals(disconnectPoem1.getFirstRow().toString(),"new ha ");
		
		Poem disconnectPoem3 = new Poem(w1,w2,1);
		model.setSelectedWordinPoem(w1);
		Row row3=new Row(w1,w2,1);
		row1.addWord(w3);
		CC1=new WordDisconnectionController(model, panel, disconnectPoem3);
		CC1.disconnectEdgeWord(2,row3);
		
		Poem disconnectPoem2 = new Poem(w1,w2,1);
		model.setSelectedWordinPoem(w1);
		Row row2=new Row(w1,w2,1);
		assertEquals(3,row1.getWordNumber());
		CC1=new WordDisconnectionController(model, panel, disconnectPoem2);
		CC1.disconnectEdgeWord(2,row2);
		assertEquals(disconnectPoem1.getFirstRow().getWordNumber(),2);
		assertEquals(disconnectPoem1.getFirstRow().toString(),"new ha ");
		
	
	}
	
	@Test 
	public void testExecuteFirstRow(){
		word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		word2 = new Word(80, 530, 30 ,14, "cat", 2);
		
		word3 = new Word(100, 550, 30,14,"dog", 2);
		word4 = new Word(100, 490, 50,14,  "white", 0);

		poem2 = new Poem(word1, word2, 2);
		poem3 = new Poem(word3, word4, 1);
		poem1 = new Poem(poem2,poem3,1);
		model.setSelectedWordinPoem(word1);
		model.setSelectedPoem(poem1);
		Row r = poem1.getFirstRow();
		CC1 = new WordDisconnectionController(model, panel, poem1);
		CC1.disconnectEdgeWord(1, r);
		assertEquals(poem1.getFirstRow().getWordNumber(), 1);
		
		model.setSelectedWordinPoem(word2);
		model.setSelectedPoem(poem1);
		r = poem1.getFirstRow();
		CC1 = new WordDisconnectionController(model, panel, poem1);
		CC1.disconnectEdgeWord(1, r);
		assertEquals(poem1.getFirstRow().getWordNumber(), 2);
		
	}
	
	@Test 
	public void testExecuteFirstRow2(){
		word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		word2 = new Word(80, 530, 30 ,14, "cat", 2);
		
		word3 = new Word(100, 550, 30,14,"dog", 2);
		word4 = new Word(100, 490, 50,14,  "white", 0);

		poem2 = new Poem(word1, word2, 2);
		poem3 = new Poem(word3, word4, 1);
		poem1 = new Poem(poem2,poem3,1);
		model.setSelectedWordinPoem(word2);
		model.setSelectedPoem(poem1);
		Row r = poem1.getFirstRow();
		CC1 = new WordDisconnectionController(model, panel, poem1);
		CC1.disconnectEdgeWord(2, r);
		assertEquals(poem1.getFirstRow().getWordNumber(), 1);
		
		model.setSelectedWordinPoem(word1);
		model.setSelectedPoem(poem1);
		r = poem1.getFirstRow();
		CC1 = new WordDisconnectionController(model, panel, poem1);
		CC1.disconnectEdgeWord(2, r);
		assertEquals(poem1.getFirstRow().getWordNumber(), 2);
		
	}
	
	@Test
	public void testExecuteLastRow(){
		word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		word2 = new Word(80, 530, 30 ,14, "cat", 2);
		
		word3 = new Word(100, 550, 30,14,"dog", 2);
		word4 = new Word(100, 490, 50,14,  "white", 0);

		poem2 = new Poem(word1, word2, 2);
		poem3 = new Poem(word3, word4, 1);
		poem1 = new Poem(poem2,poem3,1);
		model.setSelectedWordinPoem(word3);
		model.setSelectedPoem(poem1);
		Row r = poem1.getLastRow();
		CC1 = new WordDisconnectionController(model, panel, poem1);
		CC1.disconnectEdgeWord(1, r);
		assertEquals(poem1.getLastRow().getWordNumber(), 1);
		model.setSelectedWordinPoem(word4);
		model.setSelectedPoem(poem1);
		r = poem1.getLastRow();
		CC1 = new WordDisconnectionController(model, panel, poem1);
		CC1.disconnectEdgeWord(1, r);
		assertEquals(poem1.getFirstRow().getWordNumber(), 2);
	}
	
	@Test
	public void testExecuteLastRow2(){
		word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		word2 = new Word(80, 530, 30 ,14, "cat", 2);
		word3 = new Word(100, 550, 30,14,"dog", 2);
		word4 = new Word(100, 490, 50,14,  "white", 0);

		poem2 = new Poem(word1, word2, 2);
		poem3 = new Poem(word3, word4, 1);
		poem1 = new Poem(poem2,poem3,1);
		model.setSelectedWordinPoem(word4);
		model.setSelectedPoem(poem1);
		Row r = poem1.getLastRow();
		CC1 = new WordDisconnectionController(model, panel, poem1);
		CC1.disconnectEdgeWord(2, r);
		assertEquals(poem1.getLastRow().getWordNumber(), 1);
		model.setSelectedWordinPoem(word3);
		model.setSelectedPoem(poem1);
		r = poem1.getLastRow();
		CC1 = new WordDisconnectionController(model, panel, poem1);
		CC1.disconnectEdgeWord(2, r);
		assertEquals(poem1.getFirstRow().getWordNumber(), 2);
	}

	
	@Test 
	public void testMiddleRow(){
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
		
		model.setSelectedWordinPoem(word1);
		model.setSelectedPoem(poem1);
		Row r = poem1.getRowByWord(word1);
		CC1 = new WordDisconnectionController(model, panel, poem1);
		CC1.disconnectEdgeWord(1, r);
		assertEquals(r.getWordNumber(), 1);
		model.setSelectedWordinPoem(word2);
		model.setSelectedPoem(poem1);
		r = poem1.getRowByWord(word2);
		CC1 = new WordDisconnectionController(model, panel, poem1);
		CC1.disconnectEdgeWord(1, r);
		assertEquals(r.getWordNumber(), 0);
	}
	
	@Test 
	public void testMiddleRow2(){
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
		
		model.setSelectedWordinPoem(word2);
		model.setSelectedPoem(poem1);
		Row r = poem1.getRowByWord(word2);
		CC1 = new WordDisconnectionController(model, panel, poem1);
		CC1.disconnectEdgeWord(2, r);
		assertEquals(r.getWordNumber(), 1);
		model.setSelectedWordinPoem(word1);
		model.setSelectedPoem(poem1);
		r = poem1.getRowByWord(word1);
		CC1 = new WordDisconnectionController(model, panel, poem1);
		CC1.disconnectEdgeWord(2, r);
		assertEquals(r.getWordNumber(), 1);
	}
}




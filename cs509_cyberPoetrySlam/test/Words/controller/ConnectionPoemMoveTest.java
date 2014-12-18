package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Poem;
import Words.model.Row;
import Words.model.Word;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class ConnectionPoemMoveTest {
	
	Poem connectPoem;
	Word selectedWord;
	Word word1, word2;
	Board b;
	Row r;
	ConnectionPoemMove CPM;

	@Before
	public void setUp() throws Exception {
		word1 = new Word(50, 600, 40,14,"full", 1);
		word2 = new Word(80,600,40,14,"score",8);
		connectPoem = new Poem(word1,word2,1);
		selectedWord = new Word(40,600, 40,14, "have",9);
		b = new Board();
		b.getWords().add(word1);
		b.getWords().add(word2);
		b.getWords().add(selectedWord);
		b.getPoems().add(connectPoem);
		
		
		
	}

	@Test
	public void testExecute() {
		CPM = new ConnectionPoemMove(connectPoem, selectedWord, b, 50,100, 1);
		CPM.execute();
		assertEquals(b.getWords().size(), 2);
		assertEquals(0,selectedWord.getX());
		assertEquals(600,selectedWord.getY());
		CPM.undo();
		assertEquals(50,selectedWord.getX());
		assertEquals(100,selectedWord.getY());
		CPM.redo();
		assertEquals(0,selectedWord.getX());
		assertEquals(600,selectedWord.getY());
	}

	@Test
	public void testExecute2() {
		CPM = new ConnectionPoemMove(connectPoem, selectedWord, b, 50,100, 2);
		CPM.execute();
		assertEquals(120,selectedWord.getX());
		assertEquals(600,selectedWord.getY());
		CPM.undo();
		assertEquals(50,selectedWord.getX());
		assertEquals(100,selectedWord.getY());
		CPM.redo();
		assertEquals(120,selectedWord.getX());
		assertEquals(600,selectedWord.getY());
	}
	


}

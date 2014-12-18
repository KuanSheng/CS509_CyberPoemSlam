package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Word;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class MoveWordTest {

	MoveWord mW;
	Word word;
	
	@Before
	public void setUp() throws Exception {
		word = new Word(50, 100, 40,14,"happy",1);
		mW = new MoveWord(word,79,90, 200, 250);
	}

	@Test
	public void testExecute() {
		mW.execute();
		assertEquals(word.getX(),200);
		assertEquals(word.getY(), 250);
		mW.undo();
		assertEquals(word.getX(),79);
		assertEquals(word.getY(),90);
		mW.redo();
		assertEquals(200, word.getX());
		assertEquals(250, word.getY());
		}

	
}

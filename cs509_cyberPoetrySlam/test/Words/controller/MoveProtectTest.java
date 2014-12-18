package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Word;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class MoveProtectTest {

	Board b;
	Word word1, word2, word3, word4,word5, word6,word7,word8;
	MoveProtect mP1, mP2;
	@Before
	public void setUp() throws Exception {
		word1 = new Word(100,200,40,14,"one",1);
		word2 = new Word(100,150,40,14,"two",2);
		word3 = new Word(100, 190, 40,14,"three",3);
		word4= new Word(100, 250, 40,13, "four",2);
		word5 = new Word(100, 600, 40,14,"five",5);
		word6= new Word(150, 550, 40,13,"six",6);
		word7= new Word(400, 580,40,14,"seven",7);
		word8 = new Word(100,610,40,14,"eight",8);
		b = new Board();
		b.getWords().add(word1);
		b.getWords().add(word2);
		b.getWords().add(word3);
		b.getWords().add(word4);
		b.getWords().add(word5);
		b.getWords().add(word6);
		b.getWords().add(word7);
		b.getWords().add(word8);
		b.getunprotectedWords().add(word5);
		b.getunprotectedWords().add(word6);
		b.getunprotectedWords().add(word7);
		b.getunprotectedWords().add(word8);
		b.getProtectedWords().add(word1);
		b.getProtectedWords().add(word2);
		b.getProtectedWords().add(word3);
		b.getProtectedWords().add(word4);
		mP1 = new MoveProtect(word1, b,200,200,300,600);
		mP2= new MoveProtect(word8,b,330,670, 100,110);
		
	}

	@Test
	public void testExecute() {
		mP1.execute();
		assertEquals(word1.getX(), 300);
		assertEquals(word1.getY(), 600);
		assertEquals(b.getProtectedWords().size(),3);
		assertEquals(b.getunprotectedWords().size(),5);
		mP1.undo();
		assertEquals(word1.getX(), 200);
		assertEquals(word1.getY(), 200);
		assertEquals(b.getProtectedWords().size(),4);
		assertEquals(b.getunprotectedWords().size(),4);
		mP1.redo();
		assertEquals(word1.getX(), 300);
		assertEquals(word1.getY(), 600);
		assertEquals(b.getProtectedWords().size(),3);
		assertEquals(b.getunprotectedWords().size(),5);
		
		mP2.execute();
		assertEquals(word8.getX(), 100);
		assertEquals(word8.getY(), 110);
		assertEquals(b.getProtectedWords().size(),4);
		assertEquals(b.getunprotectedWords().size(),4);
		mP2.undo();
		assertEquals(word8.getX(), 330);
		assertEquals(word8.getY(), 670);
		assertEquals(b.getProtectedWords().size(),3);
		assertEquals(b.getunprotectedWords().size(),5);
		mP2.redo();
		assertEquals(word8.getX(), 100);
		assertEquals(word8.getY(), 110);
		assertEquals(b.getProtectedWords().size(),4);
		assertEquals(b.getunprotectedWords().size(),4);
		
	}


}

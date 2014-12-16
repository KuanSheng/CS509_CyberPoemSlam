package Words.model;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class RowTest {
	Row row1, row2, row3;
	Word word1,word2, word3, word4;
	Word word5;

	@Before
	public void setUp() throws Exception {
		row1 = new Row(50,100, 20, 90);
		
		word1 = new Word(60, 600, 40, 20, "lovely", 0 );
		word2 = new Word(80, 530, 30 ,20, "cat", 2);
		row2 = new Row(word1,word2, 2);
		
		word3 = new Word(100, 550, 30,20,"dog", 2);
		word4 = new Word(100, 490, 50,20,  "white", 0);
		row3 = new Row(word3, word4, 1);
		
		word5 = new Word(60,570,40,20,"Happy",1);
	}

	@Test
	public void testRowIntt() {
		assertEquals(50, row1.getX());
		assertEquals(100, row1.getY());
		assertEquals(20, row1.getHeight());
		assertEquals(90, row1.getWidth());
		assertEquals(2, row1.type);
	}

	@Test
	public void testRowWordWord() {
		
		assertEquals(60, row2.getX());
		assertEquals(600,row2.getY());
		assertEquals(row2.words, row2.getWords());
		assertEquals(row2.WordNumber, 2);
		assertEquals(word1, row2.getFirstWord());
		assertEquals(word2, row2.getLastWord());
		assertEquals(70, row2.getWidth());
		assertEquals(20, row2.getHeight());
		assertEquals(word1, row2.getFormerWord(word2));
		assertEquals(null, row2.getFormerWord(word1));
		assertEquals(null, row2.getNextWord(word2));
		assertEquals(word2, row2.getNextWord(word1));
		
		
		
		assertEquals(70, row3.getX());
		assertEquals(490, row3.getY());
		assertEquals(row3.WordNumber, 2);
		assertEquals(word4, row3.getLastWord());
		assertEquals(word3, row3.getFirstWord());
		assertEquals(80, row3.getWidth());
		assertEquals(20, row3.getHeight());
		assertEquals(word3, row3.getFormerWord(word4));
		assertEquals(null, row3.getFormerWord(word3));
		assertEquals(null, row3.getNextWord(word4));
		assertEquals(word4, row3.getNextWord(word3));
		
		row2.setLocation(200, 700, 60 ,600);
		assertEquals(200,row2.getX());
		assertEquals(700, row2.getY());
		assertEquals(200, word1.getX());
		assertEquals(700, word1.getY());
	}
	
	@Test 
	public void testRemoveWord(){
		row2.removeWord(word1);
		assertEquals(1, row2.getWordNumber());
		assertEquals(30, row2.getWidth());
	//	assertEquals(100, row2.getX());
	//	assertEquals(600, row2.getY());
	//	assertEquals(word2, row2.getLastWord());
	//  assertEquals(word2, row2.getFirstWord());
		System.out.println(row2.getFirstWord());
		System.out.println(row2.getLastWord());
		
	//  assertEquals(word5, row2.getNextWord(word2));
		System.out.println(row2.getNextWord(word2));
		System.out.println(row2.getFormerWord(word5));
	}

	
	@Test
	public void testAddWord() {
		row2.addWord(word5);
		assertEquals(3, row2.getWordNumber());
		assertEquals(110, row2.getWidth());	
		assertEquals(word1, row2.getFirstWord());
		//assertEquals(word5, row2.getLastWord());
		System.out.println(row2.getLastWord());

	}
	


	@Test
	public void testIntersection() {
		assertEquals(false, row1.intersection(40,110));
		assertEquals(false, row1.intersection(200, 110));
		assertEquals(false, row1.intersection(60, 79));
		assertEquals(false, row1.intersection(60, 150));
		assertEquals(true, row1.intersection(60, 110));
	}

	@Test public void testSetLocationAfterConnection(){
		row1.setLocationAfterConnection(200, 40);
		assertEquals(200, row1.getX());
		assertEquals(40, row1.getY());
	}


	@Test
	public void testIterator() {
		Iterator<Word> it= row2.iterator();
		assertEquals(word1, it.next());
		assertEquals(word2, it.next());
	}

	@Test
	public void testToString(){
		assertEquals("lovely cat ", row2.toString());
		assertEquals("dog white ", row3.toString());
	}
}

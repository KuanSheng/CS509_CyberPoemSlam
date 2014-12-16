package Words.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WordTest {

	 Word word= new Word(40, 490, 10, 20, "Happy", 1);
	
	@Before
	public void setUp() throws Exception {
		
		
	}

	@Test
	public void testGetX() {
		assertEquals(40, word.getX());
	}

	@Test
	public void testGetY() {
		assertEquals(490, word.getY());
	}

	@Test
	public void testGetWidth() {
		assertEquals(10, word.getWidth());
	}

	@Test
	public void testGetHeight() {
		assertEquals(20, word.getHeight());
	}

	@Test
	public void testGetValue() {
		assertEquals("Happy", word.getValue());
	}

	@Test
	public void testGetWordType(){
		assertEquals(1, word.getWordType());
	}


	@Test
	public void testIsProtected() {
		Word word1=new Word(300, 544, 10, 13, "New", 1);
		assertEquals(false, word1.isProtected());
		assertEquals(true, word.isProtected());
	}

	@Test
	public void testIntersection() {
		assertEquals(false, word.intersection(30, 100));
		assertEquals(false, word.intersection(100, 100));
		assertEquals(false, word.intersection(45,400));
		assertEquals(false, word.intersection(45, 530));
		assertEquals(true, word.intersection(45,500));
		
	}
	
	@Test
	public void testOverlap(){
		
		Word T1 = new Word(45,480, 10,20, "Happy", 1 );		
        assertEquals(true, word.overlap(T1));      
        
		
		Word T2 = new Word(35,500, 10,20,"Happy",1);
		assertEquals(true, word.overlap(T2));
		
		Word T3 = new Word(35, 480, 10, 20, "Happy", 1);
		assertEquals(true, word.overlap(T3));
		
		Word T4 = new Word(45, 500, 10,20, "Happy", 1);
		assertEquals(true, word.overlap(T4));
		
		Word T5 = new Word(35,490, 10, 20, "Happy", 1);
		assertEquals(true, word.overlap(T5));
		
		Word T6 = new Word(35,490, 30, 20, "Happy", 1);
		assertEquals(true, word.overlap(T6));
		
		Word T7 = new Word(70,490, 10, 20, "Happy",1);
		assertEquals(false, word.overlap(T7));
		
	}
	
	
	@Test
	public void testOverlapRow(){
		Row T1 = new Row(45, 480, 20,10);	
        assertEquals(true, word.overlapRow(T1));   
        
		Row T2 = new Row(35,500, 20,10);
		assertEquals(true, word.overlapRow(T2));
		
		Row T3 = new Row(35, 480, 20, 10);
		assertEquals(true, word.overlapRow(T3));
		
		Row T4 = new Row(45, 500,20, 10);
		assertEquals(true, word.overlapRow(T4));
		
		Row T5 = new Row(35,490, 20, 10);
		assertEquals(true, word.overlapRow(T5));
		
		Row T6 = new Row(35,490, 20, 30);
		assertEquals(true, word.overlapRow(T6));
		
		Row T7 = new Row(70,490, 20, 10);
		assertEquals(false, word.overlapRow(T7));
		
	}


	@Test
	public void testSetLocation() {
		word.setLocation(300, 100);
		assertEquals(300, word.getX());
		assertEquals(100, word.getY());
	}
	
	@Test
	public void testToString(){
		assertEquals("Happy", word.toString());
	}
	
	@Test
	public void testGetTypeInt( ){
		String s;
		s = "verb";
		assertEquals(3, Word.getTypeInt(s));
		s = "noun";
		assertEquals(2, Word.getTypeInt(s));
		s = "adv";
		assertEquals(1, Word.getTypeInt(s));
		s = "adj";
		assertEquals(0, Word.getTypeInt(s));
		s = "nontype";
		assertEquals(-1, Word.getTypeInt(s));
		
	}
	
	
}
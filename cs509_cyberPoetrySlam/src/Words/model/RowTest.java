package Words.model;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class RowTest {
	private Row row = new Row(50,100, 20, 90);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRowIntIntIntInt() {
		assertEquals(50, row.getX());
		assertEquals(100, row.getY());
		assertEquals(20, row.getHeight());
		assertEquals(90, row.getWidth());
//		assertEquals(2, row.type);
	}

	@Test
	public void testRowWordWordInt() {
		Word w1= new Word(10,20,20,20,"Happy",1);
		Word w2=new Word(40,10,40, 20 ,"New",1);
		Row row1=new Row(w1,w2,1);
		assertEquals(10, row1.getX());
		assertEquals(20,row1.getY());
		assertEquals(30, w2.getX());
		assertEquals(20, w2.getY());
		
		assertEquals(row1.words, row1.getWords());
		
		assertEquals(w1, row1.getFirstWord());
		
		Word w3= new Word(10,20,20,20,"Happy",1);
		Word w4=new Word(40,10,40, 20 ,"New",1);
		Row row2=new Row(w3,w4,0);
		assertEquals(20, row2.getX());
		assertEquals(10, row2.getY());
		assertEquals(20,w3.getX());
		assertEquals(10, w3.getY());
		
		row2.setLocation(100, 200);
		assertEquals(100,row2.getX());
		assertEquals(200, row2.getY());
	//	assertEquals(100, w3.getX());
	//	assertEquals(140, w4.getX());
		
		row2.removeWord(w3);
		assertEquals(1, row2.getWordNumber());
		assertEquals(40, row2.getWidth());
		
		
	}

	
	@Test
	public void testAddWord() {
		Word w1= new Word(60,100,20,20,"Happy",1);
		row.addWord(w1);
		assertEquals(1,row.wordNumber );
		assertEquals(1, row.getWordNumber());
		assertEquals(110, row.getWidth());	
		
		assertEquals(null, row.getFirstWord());
	}
	


	@Test
	public void testIntersection() {
		assertEquals(false, row.intersection(40,110));
		assertEquals(false, row.intersection(200, 110));
		assertEquals(false, row.intersection(60, 79));
		assertEquals(false, row.intersection(60, 150));
		assertEquals(true, row.intersection(60, 110));
	}

	@Test public void testSetLocationAfterConnection(){
		row.setLocationAfterConnection(200, 40);
		assertEquals(200, row.getX());
		assertEquals(40, row.getY());
	}


	@Test
	public void testIterator() {
	
		Word w1= new Word(10,20,20,20,"Happy",1);
		Word w2=new Word(40,10,40, 20 ,"New",1);
		Row row1=new Row(w1,w2,1);
		Iterator<Word> it= row1.iterator();
		//assertEquals(10, it.next());
	}

}

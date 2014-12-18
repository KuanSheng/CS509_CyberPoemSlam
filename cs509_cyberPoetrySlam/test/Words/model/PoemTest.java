package Words.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class PoemTest {
	
	Poem poem1, poem2, poem3, poem4, poem5;
	ArrayList<Row> rows = new ArrayList<Row>();
	int x;
    int y;
    int max_x;
    int max_y;
    int min_x;
    int min_y;
    int RowNumber;
    
    Row FirstRow ;
    Row LastRow ;
    
	Row row2, row3;
	Word word1,word2, word3, word4;

	@Before
	public void setUp() throws Exception {
		word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		word2 = new Word(80, 530, 30 ,14, "cat", 2);
		row2 = new Row(word1,word2, 2);
		
		word3 = new Word(100, 550, 30,14,"dog", 2);
		word4 = new Word(100, 490, 50,14,  "white", 0);
		row3 = new Row(word3, word4, 1);
		
		poem1 = new Poem (100,200);
		poem2 = new Poem(word1, word2, 2);
		poem3 = new Poem(word3, word4, 1);
		
	}

	@Test
	public void testPoemWordInt() {
		assertEquals(100, poem1.getX());
		assertEquals(200, poem1.getY());
		assertEquals(0, poem1.getRowNumber());
		assertEquals(null, poem1.getFirstRow());
		assertEquals(null, poem1.getLastRow());
		
		assertEquals("lovely cat ", poem2.getFirstRow().toString());
		assertEquals("lovely cat ", poem2.getLastRow().toString());
		assertEquals(null, poem2.getFirstRow().getFormerRow());
		assertEquals(null, poem2.getLastRow().getNextRow());
		assertEquals(60, poem2.getMin_x());
		assertEquals(130, poem2.getMax_x());
		assertEquals(600, poem2.getMin_y());
		assertEquals(614, poem2.getMax_y());		
		
	}


	@Test
	public void testPoemPoemInt() {
		poem4 = new Poem(poem2, poem3, 1);
	    assertEquals(70, poem4.getX());
	    assertEquals(476, poem4.getY());
	    assertEquals("lovely cat ", poem4.getFirstRow().toString());
	    assertEquals("dog white ", poem4.getLastRow().toString());
		assertEquals("dog white ", poem4.getFirstRow().getNextRow().toString());
		assertEquals("lovely cat ", poem4.getLastRow().getFormerRow().toString());
		assertEquals("dog white ",poem4.getFirstRow().getNextRow().toString());
		assertEquals(70, poem4.getMin_x());
		assertEquals(476, poem4.getMin_y());
		assertEquals(150, poem4.getMax_x());
		assertEquals(476+28, poem4.getMax_y());
		assertEquals(poem4.toString(), "lovely cat "+"\n"+"dog white "+ "\n");
		
		Row r = poem4.getFirstRow();
		assertEquals(r , poem4.getRowByWord(word1) );
		assertEquals(null, poem2.getRowByWord(word3));
			
		
		poem5 = new Poem(poem2, poem3, 2);
		assertEquals(490+28, poem5.getMax_y());
		assertEquals(150, poem5.getMax_x());
		assertEquals("dog white ", poem5.getFirstRow().toString());
		assertEquals("lovely cat ", poem5.getLastRow().toString());
	//	assertEquals(poem5.toString(), "dog white "+"\n" + "lovely cat " +"\n");
		System.out.println(poem5.toString());
		
		poem5.setLastRow(poem5.getFirstRow());
		assertEquals("dog white ", poem5.getLastRow().toString());		
		
		poem4.removeRow(poem4.getFirstRow());
		assertEquals(1, poem4.getRowNumber());
		
		
	}

	
	@Test
	public void testOverLapRow(){;
		Word word6 = new Word(150,476,40,14,"Happy",1);
		poem4 = new Poem(poem2, poem3, 1);
		assertEquals(null, poem4.getOverlapRow(word6));
		assertEquals(true, poem4.intersection(71,480));
		assertEquals(false, poem4.intersection(150, 476));
		
		Word word5 = new Word(60,476,40,14,"Happy",1);
		assertEquals(poem4.getFirstRow(), poem4.getOverlapRow(word5));
		poem4.addWord(word5);
		assertEquals(poem4.getFirstRow().getWordNumber(), 3);
		System.out.println(poem4.getFirstRow());
		
	}
	




}

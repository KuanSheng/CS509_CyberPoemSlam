package Words.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PoemTest {
	
	private Poem poem = new Poem (100,200);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPoemIntInt() {
		assertEquals(100, poem.getX());
		assertEquals(200, poem.getY());
	}


	@Test
	public void testPoemWordWordInt() {
		Word w1= new Word(10,20,20,20,"Happy",1);
		Word w2=new Word(40,10,40, 20 ,"New",1);
		Poem poem1=new Poem(w1,w2,1);
		
		assertEquals(1,poem1.getRowNumber());
		assertEquals(10, poem1.getX());
		assertEquals(20, poem1.getY());
		
		assertEquals(true, poem1.intersection(15, 30));
		assertEquals(false, poem1.intersection(5, 100));
		
		Word w3= new Word(5,20,20,20,"Happy",1);
		poem1.getOverlapRow(w3);
		poem1.addWord(w3);
		
		Word w4= new Word(200,20,20,20,"Happy",1);
		poem1.getOverlapRow(w4);
		poem1.addWord(w4);
		
		poem1.setLocation(35,40);
		assertEquals(35, poem1.getX());
		assertEquals(40, poem1.getY());
		
		
	}

	@Test
	public void testReturnRows(){
		assertEquals(poem.rows, poem.getRows());
	}

	@Test
	public void testRemoveRow() {
		Word w1= new Word(10,20,20,20,"Happy",1);
		Word w2=new Word(40,10,40, 20 ,"New",1);
		Poem poem1=new Poem(w1,w2,1);
		
		Word w3= new Word(5,20,20,20,"Happy",1);
	
		poem1.removeRow(poem1.getOverlapRow(w3));
		assertEquals(0, poem1.getRowNumber());
	}



}

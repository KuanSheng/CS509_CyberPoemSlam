package Words.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class ModelTest {
	
	Board b=new Board();
	Model m=new Model(b);
	Model m1= new Model();
	

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSetBoard() {
		m.setBoard(b);
		assertEquals(b, m.board);
		Model m2= new Model();
		m2.restore(m);
		assertEquals(b, m2.board);
	}

	@Test
	public void testGetBoard() {
	    assertEquals(b,m.getBoard());
	}

	@Test
	public void testSetSelected() {
		Word s= new Word(10,20,20,20,"Happy",1);
		b.addWords(s);
		m=new Model(b);
		m.setSelected(s);
		m.toString();
		assertEquals(s, m.getSelected());
	}

	@Test
	public void testSetSelectedPoem() {
		Poem p=new Poem(50,100);
		m.setSelectedPoem(p);
		
		assertEquals(p, m.getSelectedPoem());
	}

	@Test
	public void testSetSelectedWordinPoem() {
		Word s= new Word(10,20,20,20,"Happy",1);
		m.setSelectedWordinPoem(s);
		
		assertEquals(s, m.getSelectedWordinPoem());
	}
	
	@Test
	public void tetHighlight(){
		Word s= new Word(10,20,20,20,"Happy",1);
		m.setHighlightWord(s);
		assertEquals(s, m.getHighlightWord());
	}

	@Test
	public void testSelectedArea(){
		m.setSelectedArea(50,100, 90,80);
		assertEquals(50, m.getSelectedArea().getX());
		assertEquals(100, m.getSelectedArea().getY());
		assertEquals(90, m.getSelectedArea().getWidth());
		assertEquals(80, m.getSelectedArea().getHeight());
	}
	
	@Test
	public void testRorPoem(){
		Word word1 = new Word(60, 600, 40, 20, "lovely", 0 );
		Word word2 = new Word(80, 530, 30 ,20, "cat", 2);
		Row row2 = new Row(word1,word2, 2);
		m.setSelectedRow(row2);
		assertEquals(row2, m.getSelectedRow());
		
		Word word3 = new Word(100, 550, 30,20,"dog", 2);
		Word word4 = new Word(100, 490, 50,20,  "white", 0);
		Poem p  = new Poem(word3, word4, 1);
		m.setSelectedPoem(p);
		assertEquals(p, m.getSelectedPoem());
		m.setSubmittedPoem(p);
		assertEquals(p, m.getSubmittedPoem());
;
		
	}

}

package Words.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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


}

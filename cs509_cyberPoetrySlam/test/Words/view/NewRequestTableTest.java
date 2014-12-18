package Words.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.OurSwap;
import Words.model.Word;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class NewRequestTableTest {
	Board b;
	OurSwap swap;
	NewRequestTable NRT;

	@Before
	public void setUp() throws Exception {
		Word w1 = new Word(50,50,40,14,"pop",1);
		b= new Board();
		swap = new OurSwap(b);
	}

	@Test
	public void testNewRequestTable() {
		NRT = new NewRequestTable(b,swap);
		NRT.refreshTable();
	}



}

package Words.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class OurSwapTest {
	Board b;
	OurSwap swap;
	Word word1,word2,word3,word4;
	

	@Before
	public void setUp() throws Exception {
		b= new Board();
		word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		word2 = new Word(80, 530, 30 ,14, "cat", 2);
		
		word3 = new Word(100, 550, 30,14,"dog", 2);
		word4 = new Word(100, 490, 50,14,  "white", 0);
		b.getunprotectedWords().add(word1);
		b.getunprotectedWords().add(word2);
		b.getunprotectedWords().add(word3);
		
		swap = new OurSwap(b);
		
	}

	@Test
	public void testOurSwap() {
		swap.addOffer(word4);
		swap.addOffer(word1);
		assertEquals(1, swap.getOurOffer().size());
	}


	@Test
	public void testAddOfferInt() {
		swap.addOffer(0);
		assertEquals(1, swap.getOurOffer().size());
		swap.removeOffer(0);
		assertEquals(0, swap.getOurOffer().size());
		swap.addOffer(5);
		assertEquals(0, swap.getOurOffer().size());
	}

	@Test
	public void testother(){
		swap.setFailure();
		assertEquals(false,swap.success);
		assertEquals(false,swap.getStatus());
		
		swap.clear();
		assertEquals(true,swap.success);
		assertEquals(0,swap.getOurOffer().size());
	}
}

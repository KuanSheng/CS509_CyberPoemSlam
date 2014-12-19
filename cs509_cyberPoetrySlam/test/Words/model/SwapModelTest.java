package Words.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class SwapModelTest {

	Board testBoard;
	Word word1,word2,word3,word4;
	SwapModel swap;
	OurSwap ours;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSwapModel() {
		Word word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		Word word2 = new Word(80, 530, 30 ,14, "cat", 2);
		Word word3 = new Word(100, 550, 30,14,"dog", 2);
		Word word4 = new Word(100, 490, 50,14,  "white", 0);
		testBoard = new Board();
		testBoard.unProtectedWords.add(word1);
		testBoard.unProtectedWords.add(word2);
		testBoard.unProtectedWords.add(word3);
		testBoard.unProtectedWords.add(word4);
		
		testBoard.addOurSwap(word1);
		assertEquals(1, testBoard.getOurSwapCount());
		assertEquals(word1,testBoard.getOurSwap(0));
		testBoard.addOurSwap(1);
		assertEquals(2, testBoard.getOurSwapCount());
      	ours = new OurSwap(testBoard);
		swap = new SwapModel(testBoard, ours);
		ours.addOffer(word1);
		ours.addOffer(word2);

	}



}

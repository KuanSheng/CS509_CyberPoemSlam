package Words.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Words.BrokerManager;
import Words.model.Board;
import Words.model.Model;
import Words.model.OurSwap;
import Words.model.Word;
import Words.view.Application;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class SwapRequestControllerTest {
	ArrayList<Word> words;
	Board b;
	Model m;
	Application app;
	Word word1,word2,word3,word4,word5;
	OurSwap swap;
	BrokerManager bm;
	SwapRequestController SRC;
	AddRandomWordForSwap addRS;
	
	@Before
	public void setUp() throws Exception {
		words = new ArrayList<Word>();
		word1 = new Word(15,300, 40,20,"my", 0);
		word2 = new Word(30,400, 50,20,"frequently",1);
		word3 = new Word(60, 500, 40,20, "book",2);
		word4 = new Word(80,600, 40,20, "success",3);
		word5 = new Word(100, 700, 40,20 ,"table",2);
		words.add(word1);words.add(word2);words.add(word3);words.add(word4);words.add(word5);
		b = new Board();
		b.setunProtectedWords(words);
		b.addOurSwap(word1);
		assertEquals(1, b.getOurSwapCount());
		assertEquals(word1,b.getOurSwap(0));
		b.addOurSwap(0);
		m = new Model(b);
		app = new Application(m);
	//	swap = new OurSwap(b);
		bm = new BrokerManager(app,m);
		app.setBroker(bm);
		bm = new BrokerManager(app,m);
		SRC = new SwapRequestController(m, app);
	}



	@Test
	public void testfillinOfferRequest() {
		
		String[] offerValue = new String[2];
		String[] offerType = new String[2];
		String[] requestType = new String[2];
		String[] requestValue = new String[2];
		SRC.fillinOffer(offerValue, offerType);
		SRC.fillinRequest(requestValue, requestType);
	}

}

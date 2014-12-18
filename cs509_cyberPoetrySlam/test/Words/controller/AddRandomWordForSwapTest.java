package Words.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.model.OurSwap;
import Words.model.Word;
import Words.view.Application;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class AddRandomWordForSwapTest {
	
	ArrayList<Word> words;
	Board b;
	Model m;
	Application app;
	OurSwap swap;
	AddRandomWordForSwap addRS;
	Word word1, word2,word3,word4,word5;

	@Before
	public void setUp() throws Exception {
		words = new ArrayList<Word>();
		word1 = new Word(15,300, 40,20,"my", 0);
		word2 = new Word(30,400, 50,20,"frequently",1);
		word3 = new Word(60, 500, 40,20, "book",2);
		word4 = new Word(80,600, 40,20, "success",3);
		word5 = new Word(100, 700, 40,20 ,"table",2);
		words.add(word1);words.add(word2);words.add(word3);words.add(word4);words.add(word5);
		b= new Board();
		b.setunProtectedWords(words);
		m = new Model(b);
		app =new Application(m);
		swap = new OurSwap(b);
		addRS = new AddRandomWordForSwap(b, swap,app);
				
	}

@Test
public void testAdd(){
	addRS.addRandomWord("noun");
	assertEquals(swap.getOurOffer().size(),0);
	assertEquals(b.getunprotectedWords().size(),5);
	addRS.addRandomWord("verb");
	assertEquals(swap.getOurOffer().size(),0);
	assertEquals(b.getunprotectedWords().size(),5);
	addRS.addRandomWord("conjunction");
	assertEquals(swap.getOurOffer().size(),1);
	assertEquals(b.getunprotectedWords().size(),4);
	addRS.addRandomWord("number");
	assertEquals(swap.getOurOffer().size(),1);
	assertEquals(b.getunprotectedWords().size(),4);
	addRS.addRandomWord("adjective");
	assertEquals(swap.getOurOffer().size(),2);
	assertEquals(b.getunprotectedWords().size(),3);
	addRS.addRandomWord("pronoun");
	assertEquals(swap.getOurOffer().size(),2);
	assertEquals(b.getunprotectedWords().size(),3);
	addRS.addRandomWord("adverb");
	assertEquals(swap.getOurOffer().size(),3);
	assertEquals(b.getunprotectedWords().size(),2);
	addRS.addRandomWord("determiner");
	assertEquals(swap.getOurOffer().size(),4);
	assertEquals(b.getunprotectedWords().size(),1);
	addRS.addRandomWord("preposition");
	assertEquals(swap.getOurOffer().size(),4);
	assertEquals(b.getunprotectedWords().size(),1);
	addRS.addRandomWord("suffix");
	assertEquals(swap.getOurOffer().size(),4);
	assertEquals(b.getunprotectedWords().size(),1);
	addRS.addRandomWord("nontype");
	assertEquals(swap.getOurOffer().size(),4);
	assertEquals(b.getunprotectedWords().size(),1);
}

}

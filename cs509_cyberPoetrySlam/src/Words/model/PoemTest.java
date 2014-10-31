package Words.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PoemTest {
	
	Poem poem = new Poem(60,100);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPoem() {
		assertEquals(60, poem.x);
		assertEquals(100, poem.y);
		assertEquals(null, poem.words);
	}

}

package Words;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.view.Application;
import broker.BrokerClient;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class BrokerManagerTest {

	Board b;
	Model model;

	Application gui;
	BrokerClient broker;
	BrokerManager bm;

	@Before
	public void setUp() throws Exception {
		
		broker = new BrokerClient("localhose", 9172);
		BrokerClient.generateID();
		String ID = broker.getID();
		b= new Board();
		model  = new Model(b);
		gui = new Application(model);
		bm = new BrokerManager(gui, model);
		
	}

	@Test
	public void testdeny() {
		bm.process(broker, "denySwapMsg"+broker.getID()+"234:5:noun:noun:noun:noun:noun:car:tree:rock:table:dog:verb:noun:*:*:*:*:book:*:*:*");
	}
	
	@Test
	public void testmatch() {
		bm.process(broker, "matchSwapMsg"+broker.getID()+"234:5:noun:noun:noun:noun:noun:car:tree:rock:table:dog:verb:noun:*:*:*:*:book:*:*:*");
	}

	@Test
	public void testconfirm() {
		bm.process(broker, "confirmSwapMsg"+broker.getID()+"234:5:noun:noun:noun:noun:noun:car:tree:rock:table:dog:verb:noun:*:*:*:*:book:*:*:*");
	}

}

package Words;

import java.awt.Graphics;
import java.util.*;

import Words.model.*;
import Words.view.*;
import broker.*;
import broker.handler.*;
import broker.util.*;

/**
 * Provides a case study in how to use the BrokerClient in your code. You should break
 * the logic into multiple controllers to handle the different responses from the broker.
 */
public class BrokerManager implements IHandleBrokerMessage {
	
	/** Need Model to be able to make any resolution. */
	Model model;
	
	/** Need Application in case need to make changes to GUI. */
	Application gui;
	
	/** Your interface to the broker. */
	BrokerClient broker;
	
	/** Thread managing connections. When you are done, call shutdown for clean exit. */
	ReaderThread thread;
	
	/** Determines connectivity. */
	boolean connected;
	
	/** Ruizhu add : need Graphics to draw add word after swap */
	Graphics g ;
	
	/** Ruizhu add: need word arraylist to store add words after swap */
	ArrayList<Word> addWords = new ArrayList<Word>();
	
	/**
	 * Will need to be changed once we decide where broker is being run.
	 * 
	 * In general, information such as this shouldn't be in the compiled Java file 
	 */
	public final static String brokerHost = "gheineman.cs.wpi.edu";
	public final static int    brokerPort = 9172;
	
	public BrokerManager(Application gui, Model model) {
		this.gui = gui;
		this.model = model;
	}
	
	/** Are we connected to the broker? */
	public boolean isConnected() { return connected; }
	
	/** Get our unique id. */
	public String getID() { 
		if (broker == null) { return null; }
		return broker.getID(); 
	}
	
	/** Cleanly shutdown interface to broker. */
	public void shutdown() { 
		if (thread != null) { thread.shutdown(); }
	}
	
	/** Try to connect to broker on default host. */
	public boolean connect() {
		return connect(brokerHost);
	}
	
	/**
	 * Try to connect to the broker.
	 * 
	 * @param host 
	 */
	public boolean connect(String host) {
		
		broker = new BrokerClient(host, brokerPort);

		if (!broker.connect()) {
			System.err.println("unable to connect to broker on " + host);
			broker.shutdown();
			return false;
		}else{
            System.out.println("Connected to broker on " + host);
        }
		
		// at this point we are connected, and we will block waiting for 
		// any messages from the broker. These will be sent to the process
		System.out.println("Connected as : " + broker.getID() + " : " + broker.getStatus());
		
		// start thread to process commands from broker.
		thread = new ReaderThread(broker, this); //start a thread to listen, when something happens,
                                                 //calls the process method below
		thread.start();
		
		// nothing more to do here. This thread is now independent and will respond
		// to broker messages, as well as our own concerns.
		return true;
	}

	/** Helper method for debugging. */
	void dumpState() {
		int countSample = 0;
		int countOther = 0;
		for (Word word : model.getBoard().getunprotectedWords()) {
			if (word.getValue().equals("Sample")) { countSample++; }
			if (word.getValue().equals("Other")) { countOther++; }
		}
		System.out.println("New State:" + countSample + " Sample, " + countOther + " Other");
	}
	
	/**
	 * Sends swap request to the broker
	 */
	public void sendMessage(String msg) {
		broker.getBrokerOutput().println(msg);
	}
	
	/**
	 * Note: Your code will have to provide a suitable implementation that integrates
	 * with your individual board.
	 * 
	 * Here is where all broker messages are received. You must check each one, and
	 * act accordingly.
	 */
	@Override
	public void process(BrokerClient broker, String msg) {
		System.out.println("Process message:" + msg);
		
		if (msg.startsWith(IProtocol.denySwapMsg)) {
			dumpState();
			System.out.println("Denied swap request");
			return;
		}
		
		// some third party has asked for a swap. Pull this out into its own controller
		if (msg.startsWith(IProtocol.matchSwapMsg)) {
			Swap s = MatchSwapMessage.getSwap(msg);
			System.out.println("Third party trying a swap:" + s.flatten());
			
			// swap is requesting words. We have to check to see if we have these
			// requested words.
			ArrayList<Word> matched = new ArrayList<Word>();
			
			boolean failed = true;
			for (int i = 0; i < s.requestWords.length; i++) {
				failed = true;
				for (Word word : model.getBoard().getunprotectedWords()) {
					if (word.getValue().equals(s.requestWords[i])) {
						matched.add(word);
						failed = false;
						break;
					}
				}
				
				if (failed) {
					System.out.println("Unable to satisfy swap request");
					broker.getBrokerOutput().println(IProtocol.denySwapMsg + 
							IProtocol.separator + s.requestor_id);
					return;
				}
			}
			
			dumpState();
			System.out.println("Accepting satisfy swap request");
			
			// what should we do? Agree of course! Here is where your code would
			// normally "convert" wildcards into actual words in your board state.
			// for now this is already assumed (note sample/other swap)
			broker.getBrokerOutput().println(IProtocol.confirmSwapMsg + IProtocol.separator + s.flatten());
			return;
		}
		
		// Execute the swap
		if (msg.startsWith(IProtocol.confirmSwapMsg)) {
			Swap s = ConfirmSwapMessage.getSwap(msg);
			
			System.out.println("Before Swap:");
			dumpState();
			
			// carry out the swap. We were the one making the request...
			String wordsToRemove[];
			String wordsTypeRemove[];
			String wordsToAdd[];
			String wordsTypeAdd[];
			int type = 0;
		//	Word wordsAdd[] = null;
			
			
			if (broker.getID().equals(s.requestor_id)) { // if we are the one who send the request JUN
				wordsToRemove = s.offerWords;
				wordsTypeRemove = s.offerTypes;
				wordsToAdd = s.requestWords;
				wordsTypeAdd = s.requestTypes;
			} else {                                      // if we are the one being requested JUN
				wordsToRemove = s.requestWords;
				wordsTypeRemove = s.requestTypes;
				wordsToAdd = s.offerWords;
				wordsTypeAdd = s.offerTypes;
			}

			// remove each word as found
			for (int i = 0; i < s.n; i++) {
				for (Word word : model.getBoard().getunprotectedWords()) {
					if (word.getValue().equals(wordsToRemove[i])) {	
						
						System.out.println(word.getValue() + word.getX() + word.getY());
					
						//model.getBoard().getunprotectedWords().remove(word);
						model.getBoard().removeWords(word);
						break;
					}
				}
			}
			
			//gui.getpanel().repaint();
			
			System.out.println("remove end");
			
				
			// now we add new shapes. (at least 40 pixels inwards on all sides)
			int width = gui.getpanel().getWidth() - 40;
			//int height = gui.getpanel().getHeight() - 40;
			Random r = new Random();
			for (int i = 0; i < s.n; i++) {
				int rx = (int) (r.nextFloat() * width); 
				int ry = (int) (r.nextFloat() * 200 + 300); 

				if(wordsTypeAdd[i].equals("noun")) type = 2; System.out.println("type :" + type);
				
				Word word = new Word(rx, ry , 120 ,14, wordsToAdd[i], type );
				
			//	gui.getpanel().paintSwapAddWord(rx, ry, g, word);
				
				model.getBoard().addWords(word);
				
				System.out.println(word.getValue()+ word.getX()+word.getY());
				
			}
			
			dumpState();
			
			// must refresh viewing area
			gui.getpanel().repaint();
			gui.getpanel().repaint();
				
			return;
		}
	}

	/**
	 * If Broker vanishes, then we disable buttons. Note there is no logic to
	 * try to reconnect to broker.
	 */
	@Override
	public void brokerGone() {
		System.err.println("Lost broker connection.");
		gui.getSwapButton().setEnabled(false);
	}
}
 
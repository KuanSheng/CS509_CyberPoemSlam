package broker.server.controller;

import broker.ipc.*;
import broker.util.*;

/**
 * Controller to handle swap requests on the server side.
 *
 * To do so, finds a random client and issues matchSwapMsg. At this point, fill in the designated 
 * id
 */
public class RequestSwapController  extends ProcessMessageHandler {
	
	/** Constructor for concrete handler. */
	public RequestSwapController(ProcessMessageHandler nextOne) {
		super(nextOne);
	}
	
	/** Act on the RequestSwap message. */
	public boolean take(String request, BrokerThread thread, StringBuilder error) {
		if (!request.startsWith(IProtocol.requestSwapMsg + IProtocol.separator)) {
			return false;
		}
		
		Swap s = RequestSwapMessage.getSwap(request);
		Client client = Broker.getRandomClient(thread);
		
		if (client == null) {
			// no one else to swap with! can simply respond with 
			// failed CONFIRM code back to originator
			thread.sendMessage(IProtocol.denySwapMsg + IProtocol.separator + thread.getID());
		} else {
			// write message to client to match this swap. note that
			// client must respond back to the broker with 
			// confirm message with ACTUAL words that will be swapped out
			s.acceptor_id = client.thread.getID();
			String matchMsg = IProtocol.matchSwapMsg + IProtocol.separator + s.flatten();
			client.thread.sendMessage(matchMsg);
		}
		
		return true;
	}
}

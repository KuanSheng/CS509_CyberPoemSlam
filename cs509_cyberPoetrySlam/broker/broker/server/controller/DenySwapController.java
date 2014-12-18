package broker.server.controller;

import broker.ipc.*;
import broker.util.*;

/**
 * A denial of a swap request has been received. This must be sent
 * directly to the requesting client "as is".
 */
public class DenySwapController extends ProcessMessageHandler {
	
	/** Constructor for concrete handler. */
	public DenySwapController(ProcessMessageHandler nextOne) {
		super(nextOne);
	}

	/** Process the DenySwap message. */
	public boolean take(String request, BrokerThread thread, StringBuilder error) {
		if (!request.startsWith(IProtocol.denySwapMsg + IProtocol.separator)) {
			return false; 
		}
		
		try {
			String id = DenySwapMessage.getRequestID(request);
	
			Client requestor = Broker.getClient(id);
			
			// tell requester of failure to find a swap
			requestor.thread.sendMessage(request);
			return true;
		} catch (Exception e) {
			error.append(e.getMessage());
			return false;
		}
	}
}
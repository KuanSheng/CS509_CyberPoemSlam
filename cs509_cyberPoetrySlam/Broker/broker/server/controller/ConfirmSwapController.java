package broker.server.controller;

import broker.ipc.*;
import broker.util.*;

/**
 * A confirmation has been received for a swap request. This must be sent
 * directly to both clients "as is".
 */
public class ConfirmSwapController extends ProcessMessageHandler {
	
	/** Constructor for concrete handler. */
	public ConfirmSwapController(ProcessMessageHandler nextOne) {
		super(nextOne);
	}

	public boolean take(String request, BrokerThread thread, StringBuilder error) {
		if (!request.startsWith(IProtocol.confirmSwapMsg + IProtocol.separator)) {
			return false;
		}
		
		try {
			Swap s = ConfirmSwapMessage.getSwap(request);
	
			Client requestor = Broker.getClient(s.requestor_id);
			Client acceptor = Broker.getClient(s.acceptor_id);
			
			// no attempt to guarantee TwoPhaseCommit property. Just get this working
			requestor.thread.sendMessage(request);
			acceptor.thread.sendMessage(request);
			return true;
		} catch (Exception e) {
			error.append(e.getMessage());
			return false;
		}
	}
}

package broker.ipc;


public abstract class ProcessMessageHandler {
	
	/** Next one in the chain. */
	ProcessMessageHandler successor;
	
	/** 
	 * Construct an element in the chain of responsibility. Last one uses null as a parameter.
	 */
	protected ProcessMessageHandler(ProcessMessageHandler nextOne) {
		successor = nextOne;
	}
	
	/** 
	 * Determine whether to take and act upon the request. 
	 */
	protected abstract boolean take (String request, BrokerThread thread, StringBuilder error);
	
	/**
	 * Process given request.
	 * 
	 * Determine whether the given request is one that should be processed. If not, then
	 * pass along to the next controller interested in processing protocol messages.	
	 * 
	 * If error during processing, pass along error.
	 * 
	 * @param request
	 * @param thread
	 * @param error
	 */
	public boolean process(String request, BrokerThread thread, StringBuilder error) {
		if (take(request, thread, error)) {
			return true;
		}
		
		if (successor == null) { return false; }
		return successor.process(request, thread, error);
	}
}
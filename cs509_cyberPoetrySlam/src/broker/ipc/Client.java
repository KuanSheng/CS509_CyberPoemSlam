package broker.ipc;

/** 
 * Client table of all currently connected clients.
 * 
 * @author heineman
 *
 */
public class Client {
	
	/** 
	 * Unique key generated by client when first run. Will be unique for
	 * duration of existence. Can be used to determine when a client
	 * reconnects after severing a connection. 
	 */
	public final String id;

	/**
	 * BrokerThread for client. Communications can be sent through its
	 * buffers.
	 */
	public final BrokerThread thread;
	
	/**
	 * Current Status.
	 */
	String status;
	
	/** Constructor once thread is available, to be paired with uniq id. */
	public Client(String id, BrokerThread thread) {
		this.id = id;
		this.thread = thread;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
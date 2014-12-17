package broker.server;

import java.io.*;

import broker.ipc.*;
import broker.server.controller.*;

/** Code to launch Broker from the command line. */ 
public class BrokerLauncher {

	/** Execute the RepositoryServer using the default port. */
	public static void main(String[] args) {
		
		// construct server and bind it to default port. Then process
		// all requests as they come in
		Broker server = new Broker();
		
		try {
			server.bind();
		} catch (IOException ioe) {
			System.err.println("Unable to launch server:" + ioe.getMessage());
			System.exit(-1);
		}

		ProcessMessageHandler confirmSwap = new ConfirmSwapController(null); 
		ProcessMessageHandler denySwap    = new DenySwapController(confirmSwap);
		ProcessMessageHandler chain       = new RequestSwapController(denySwap);
		
		
		// process all requests and exit.
		System.out.println("Server awaiting client connections");
		try {
			server.process(chain);
			System.out.println("Server shutting down.");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}		
	} 
}

package Words.controller;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.util.Scanner;

import Words.BrokerManager;
import Words.model.Model;
import Words.model.Word;
import Words.view.Application;
import Words.view.ApplicationCanvas;
import broker.util.IProtocol;
import broker.util.Swap;


public class SwapRequestController extends MouseAdapter{
	
	int N ;
	String[] offerType, offerWords, requestType, requestWords;
	
	String requestMsg;
	
    public String generateMsg(){
    	
    	Scanner sc= new Scanner(System.in);
    	System.out.println("Please enter the number of words you will swap");
    	N=sc.nextInt();
    	
    	offerType=new String[N];
    	offerWords= new String[N];
    	requestType=new String[N];
    	requestWords=new String[N];
    	
    	System.out.println("Please enter the types of the words you offer");
    	for(int i=0; i < N; i++){
    		offerType[i]= sc.next();
    	}
    	
    	System.out.println("Please enter the words you offer");
    	for (int i =0 ; i < N; i++){
    		offerWords[i] = sc.next();
    	}
    	
    	System.out.println("Please enter the types of words you request");
    	for(int i= 0; i < N; i++){
    		requestType[i]=sc.next();
    	}
    	
    	System.out.println("Please enter the words you request");
    	for(int i= 0; i < N; i++){
    		requestWords[i]=sc.next();
    	}	
    	
    	
    	StringBuffer sb = new StringBuffer();
    	sb.append(N); 
		for (int i = 0; i < N; i++) {
			sb.append(IProtocol.separator); sb.append(offerType[i]); 
		}
		for (int i = 0; i < N; i++) {
			sb.append(IProtocol.separator); sb.append(offerWords[i]); 
		}
		for (int i = 0; i < N; i++) {
			 sb.append(IProtocol.separator); sb.append(requestType[i]);
		}
		for (int i = 0; i < N; i++) {
			 sb.append(IProtocol.separator); sb.append(requestWords[i]);
		}
		
		System.out.println("swap msg :" + sb.toString());
		
		return sb.toString();
    	
    }
	
	
//--------from example
	/** Needed for controller behavior. */
	Model model;
	Application app;
	
	/** Constructor holds onto key manager objects. */
	public SwapRequestController(Model model,  Application app) {
		this.model = model;
		this.app = app;
	}

	/** Act immediately */
	public void process(boolean sampForSamp) {
		
		// quickly determine which kind of swap we are making
		String offer = "Sample";
		String offerType = "noun";
		String request = "Other";
		String requestType = "noun";
		if (!sampForSamp) {
			offer = "Other";
			request = "Sample";
		}
		
		// see if we have word we claim to be offering
		boolean found = false;
		for (Word w : model.getBoard().getunprotectedWords()) {
			if (w.getValue().equals(offer)) {
				found = true;
				break;
			}
		}
		if (!found) {
			Toolkit.getDefaultToolkit().beep();
			return;
		}
		
		// let's make the request. We are requesting, and we want to have requested word for
		// the offer word.
		BrokerManager broker = app.getBroker();
		
		Swap swapRequest = new Swap(broker.getID(), "*", 1, 
				new String[] {offerType}, new String[] { offer },
				new String[] {requestType}, new String[] { request} );
		
		String swapMsg = IProtocol.requestSwapMsg + IProtocol.separator + swapRequest.flatten();
		broker.sendMessage(swapMsg);
	}	
	
	
	
	
	

}

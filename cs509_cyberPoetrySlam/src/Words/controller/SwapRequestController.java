package Words.controller;

import java.util.Scanner;


public class SwapRequestController {
	
	int N ;
	String[] offerType, offerWords, requestType, requestWords;
	
	String requestMsg;
	
    public void typeInto(){
    	
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
    		requestType[N]=sc.next();
    	}
    	
    	System.out.println("Please enter the words you request");
    	for(int i= 0; i < N; i++){
    		requestWords[N]=sc.next();
    	}	
    	
    }
	
	
	public String generateRequest(int N, String[] offerType, String[]offerwords, String[]requestType, String[]requestWords){
		
		String number= String.valueOf(N);
		String myType = null;
		String myWords = null;
		String reqType = null;
		String reqWords = null;
		
		for(int i = 0; i < N; i++){
			myType = ":" + offerType[i];
			myWords = ":" + offerWords[i];
			reqType = ":" + requestType[i];
			reqWords = ":" + requestWords[i];
		}
		
		requestMsg = number + myType + myWords + reqType +reqWords;
						
		return requestMsg;
	}
	
	
	
	
	

}

package Words.controller;

import Words.model.Model;
import Words.model.Word;
import Words.view.ApplicationCanvas;
//import com.sun.org.apache.xpath.internal.operations.Mod;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.Files;
import java.util.Iterator;

/**
 * Created by Jun on 10/28/2014.
 */
public class StoreStateController extends WindowAdapter {
    private Model model;
    private ApplicationCanvas panel;

    public StoreStateController(Model model, ApplicationCanvas panel){
        this.model = model;
        this.panel = panel;
    }

    private void storeState(){
        System.out.println("entering storeState : StoareStateContrller");
        try {
            File board = new File(System.getProperty("user.dir")+"/temp/board.ser");
            System.out.println("file not exist? : storeState.StoreStateController" );
//            if(board.exists() || !board.isDirectory()){
            if(!board.exists()){
                System.out.println("try to create file : storeState.StoareStateContrller");
                board = new File(System.getProperty("user.dir")+"/temp/board.ser");
//                System.out.println("make directory " + board.mkdir());
                board.getParentFile().mkdirs();
                System.out.println(" created file : storeState.StoareStateContrller");
            }
            System.out.println("before fileout : storeState.StoareStateContrller");
            FileOutputStream fileOut = new FileOutputStream(board);
            System.out.println("file out : storeState.StoareStateContrller");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(model);
            System.out.println("written out : storeState.StoareStateContrller");
            out.close();
            fileOut.close();
            System.out.println("file closed : storeState.StoareStateContrller");
        }catch (IOException i){
            System.out.println("im here: storeState.StoreStateController");
            i.printStackTrace();
        }
        System.out.println("stored state of board" + model.toString() + " : StoreStateController");
//        System.out.println("");
    }

    private void restoreState(){
        System.out.println("entering restoreState : StoreStateController");
        try{
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir")+"/temp/board.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            //restore state
            Model stored = (Model) in.readObject();
            model.getBoard().getWords().addAll( stored.getBoard().getWords() );
            model.getBoard().getPoems().addAll( stored.getBoard().getPoems() );
            System.out.println("deserialized model : StoreStateController");

        }catch (IOException i){
            i.printStackTrace();
            return;
        }catch (ClassNotFoundException c){
            System.out.println(" Model class not found : StoreStateController");
            return;
        }
        Iterator<Word> it = model.getBoard().iterator();
        while (it.hasNext()){
            System.out.println(" another word " + it.next() +  " : restoreState.StoreStateController");
        }
        System.out.println("model restored from file: "+model + " : restoreState . StoreStateController");
        panel.repaint();
    }
    public void windowClosing(WindowEvent e){
        storeState();
        System.out.println("window closing : windowClosing.StoreStateController");
        System.exit(0);
    }

    public void windowOpened(WindowEvent e){
        System.out.println("");
        restoreState();
    }
}


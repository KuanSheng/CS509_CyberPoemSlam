package Words.controller;

import Words.model.Model;
import Words.model.Word;
import Words.view.ApplicationCanvas;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
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
            if(board.exists() && !board.isDirectory()){
                board = new File(System.getProperty("user.dir")+"/temp/board.ser");
                board.mkdir();
            }
//            File f = new File(Path, FileName);
//            f.mkdirs();
//            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir")+"/temp/board.ser");
            FileOutputStream fileOut = new FileOutputStream(board);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(model);
            out.close();
            fileOut.close();
        }catch (IOException i){
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


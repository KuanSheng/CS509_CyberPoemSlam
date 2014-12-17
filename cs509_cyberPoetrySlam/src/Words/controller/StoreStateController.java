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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Jun on 10/28/2014.
 */
public class StoreStateController extends WindowAdapter {
     public static final String WORDS_FILE = System.getProperty("user.dir")+"/words.txt";
     public static final int INITIAL_WORD_NUMBER = 50;
     Model model;
     ApplicationCanvas panel;

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
            model.getBoard().getunprotectedWords().addAll( stored.getBoard().getunprotectedWords());
            model.getBoard().getProtectedWords().addAll( stored.getBoard().getProtectedWords() );
            model.getBoard().getPoems().addAll( stored.getBoard().getPoems() );
            System.out.println("deserialized model : StoreStateController");

        }catch (IOException i){
            System.out.println("restoring from file failed, assigning random words from words.txt...");
            ArrayList<Word> pool = readinWords();
            ArrayList<Word> samples = assignWords(pool, INITIAL_WORD_NUMBER);
            for(Word w : samples){
                model.getBoard().addWords(w);
            }
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

    ArrayList<Word> readinWords(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(WORDS_FILE));
            ArrayList<Word> newWords = new ArrayList<Word>();
            String wordInFile = reader.readLine();
            while (wordInFile != null) {
//                for(int i = 0; i < INITIAL_WORD_NUMBER; i ++){
                    String[] wordSignature = wordInFile.split("\\s+");
                    Word w = createWord(wordSignature[0], wordSignature[1]);
                    newWords.add(w);
                    wordInFile = reader.readLine();
//                }
            }
            reader.close();
            return newWords;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    Word createWord(String value, String type){
        int screenWidth = panel.getWidth() - 40; //copied code from brokerManager JUN
        Random r = new Random();
        int rx = (int) (r.nextFloat() * screenWidth);
        int ry = (int) (r.nextFloat() * 200 + 300);
        Word w = new Word(rx, ry, 120, 14, value, Word.getTypeInt(type));
        return w;

    }

    ArrayList<Word> assignWords(ArrayList<Word> pool, int count){
        if(pool == null || pool.size() == 0 || count <1) return null;
        int poolSize = pool.size();
        ArrayList<Word> samples = new ArrayList<Word>();
        Random rn = new Random();
        for(int i = 0; i < count; i ++){
            samples.add( pool.get(rn.nextInt(poolSize-1)) );
        }
        return samples;
    }
}

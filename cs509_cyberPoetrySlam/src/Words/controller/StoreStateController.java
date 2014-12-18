package Words.controller;

import Words.model.Board;
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
 * this controller stores state at closing, and restores state when starting a game.
 * Created by Jun on 10/28/2014.
 */
public class StoreStateController extends WindowAdapter {
     public static final String WORDS_FILE = System.getProperty("user.dir")+"/words.txt";
     public static final int INITIAL_WORD_NUMBER = 50;
     Model model;
     Board b;;
     ApplicationCanvas panel;

    /**
     * constructor
     * @param model gui
     * @param panel model
     */
    public StoreStateController(Model model, ApplicationCanvas panel){
        this.model = model;
        this.panel = panel;
        this.b = model.getBoard();
    }

    /**
     * write the state of the model to the file "board.ser"
     */
    private void storeState(){
        System.out.println("entering storeState : StoareStateContrller");
        try {
            File board = new File(System.getProperty("user.dir")+"/temp/board.ser");
            if(!board.exists()){
                board = new File(System.getProperty("user.dir")+"/temp/board.ser");
                board.getParentFile().mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(board);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(model);
            out.close();
            fileOut.close();
        }catch (IOException i){
            i.printStackTrace();
        }
        System.out.println("stored state of board" + model.toString() + " : StoreStateController");
    }

    /**
     * restore state from previously saved "board.ser"
     * create new initial words, if "board,ser" dosn't exist
     */
    private void restoreState(){
        System.out.println("entering restoreState : StoreStateController");
        try{
            //try restore state from stored file
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
            //create new words -- no state file was found
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

    /**
     * stores state
     * @param e
     */
    public void windowClosing(WindowEvent e){
        storeState();
        System.out.println("window closing : windowClosing.StoreStateController");
        System.exit(0);
    }

    /**
     * restores state or create new words
     * @param e
     */
    public void windowOpened(WindowEvent e){
        System.out.println("");
        restoreState();
    }

    /**
     * read in pool of words from "words.txt"
     * @return the arraylist of words in the words.txt if success, null otherwise
     */
    ArrayList<Word> readinWords(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(WORDS_FILE));
            ArrayList<Word> newWords = new ArrayList<Word>();
            String wordInFile = reader.readLine();
            while (wordInFile != null) {
                    String[] wordSignature = wordInFile.split("\\s+");
                    Word w = createWord(wordSignature[0], wordSignature[1]);
                    newWords.add(w);
                    wordInFile = reader.readLine();
            }
            reader.close();
            return newWords;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * create word object with type and value
     * @param value value of new word
     * @param type type of new word
     * @return the new word created
     */
    Word createWord(String value, String type){
        int screenWidth = panel.getWidth() - 40; //copied code from brokerManager JUN
        Random r = new Random();
        int rx = (int) (r.nextFloat() * screenWidth);
        int ry = (int) (r.nextFloat() * 200 + 300);
        Word w = new Word(rx, ry, 120, 14, value, Word.getTypeInt(type));
        return w;

    }

    /**
     * randomly assign some words from the pool to the player
     * @param pool the words to be chosen from
     * @param count the number of words to be chosen
     * @return the ArrayList of words chosen if success, null otherwise ( pool == null or count < 1)
     */
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

package Words.controller;

import Words.model.Board;
import Words.model.Model;
import Words.model.Word;
import Words.view.ApplicationCanvas;
import Words.view.WordTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

/**
 * Created by Jun on 12/13/2014.
 */
public class SortWordsController{
    WordTable table;
    Model m;
    ApplicationCanvas panel;
    Board board;

    public SortWordsController(Model m, ApplicationCanvas panel) {
        this.m = m;
        this.panel = panel;
        this.board = m.getBoard();
    }

    public SortWordsController(WordTable table, Board board){
        this.table = table;
        this.board = board;
    }
    public boolean process(MouseEvent e){
        switch ( table.getJtable().columnAtPoint(e.getPoint())){
            case 0 :
                sortByAlphabet(board.getunprotectedWords());
                return true;
            case 1 :
                sortByType(board.getunprotectedWords());
                return true;
        }
        return false;
    }

    private void sortByType(ArrayList<Word> words) {
        HashMap<Integer, ArrayList<Word>> mapByType = new HashMap<Integer, ArrayList<Word>>();
        ArrayList<Word> adj = new ArrayList<Word>();
        ArrayList<Word> adv = new ArrayList<Word>();
        ArrayList<Word> noun = new ArrayList<Word>();
        ArrayList<Word> verb = new ArrayList<Word>();

        /**add each word into corresponding list */
        for(Word w : board.getunprotectedWords()){
            switch (w.getWordType()){
                case Word.ADJ_INT :
                    adj.add(w);
                    break;
                case Word.ADV_INT :
                    adv.add(w);
                    break;
                case Word.NOUN_INT :
                    noun.add(w);
                    break;
                case Word.VERB_INT :
                    verb.add(w);
                    break;
            }
        }

        /** sort words of each type*/
        sortByAlphabet(adj);sortByAlphabet(adv);sortByAlphabet(noun);sortByAlphabet(verb);

        /**add sorted words back*/
        adj.addAll(adv);
        adj.addAll(verb);
        adj.addAll(noun);
        words.clear();
        words.addAll(adj);


    }

    public ArrayList<Word> sortByAlphabet(ArrayList<Word> words){
        TreeMap<String, ArrayList<Word>> tree = new TreeMap<String, ArrayList<Word>>(new MyStringComparator());
        for(Word w : words){
            if(tree.containsKey(w.getValue())){
                tree.get(w.getValue()).add(w);
            }else {
                ArrayList<Word> another = new ArrayList<Word>();
                another.add(w);
                tree.put(w.getValue(), another);
            }
        }
        Iterator it = tree.values().iterator();
        words.clear();
        while (it.hasNext()){
            words.addAll((ArrayList<Word>)it.next());
        }
        return words;
    }

    /**
     * Comparator used by TreeMap to compare strings
     */
    private class MyStringComparator implements Comparator {
        /**
         * Compare two strings
         * @param o1 one string
         * @param o2 anther string
         * @return 1 if o1 is "larger", 0 if two are equal, -1 is o2 is larger, -2 if arguments are not String
         */
        @Override
        public int compare(Object o1, Object o2){
            if(!String.class.isInstance(o1) || !String.class.isInstance(o2)){
                return -2;
            }
            if(((String)o1).length() <= ((String)o2).length()){
                return compare((String)o1, (String)o2);
            }else {
                return -compare((String)o2, (String)o1);
            }
        }

        private int compare(String shorter, String longer){
            for(int i = 0; i < shorter.length(); i ++){
//                System.out.println(shorter + " is larger than " + longer + " at index " + i + ((shorter.charAt(i) > longer.charAt(i))));
                if(shorter.charAt(i) > longer.charAt(i)){
                    return 1;
                }else if(shorter.charAt(i) < longer.charAt(i)){
                    return -1;
                }
            }
            if(shorter.length() == longer.length()){
//                System.out.println(shorter + " equals " + longer);
                return 0;
            }
//            System.out.println(shorter + " is smaller " + longer);
            return -1;
        }
    }



}

package Words.controller;

import Words.model.Board;
import Words.model.Model;
import Words.model.Word;
import Words.view.ApplicationCanvas;

import javax.management.modelmbean.InvalidTargetObjectTypeException;
import java.util.*;

/**
 * Created by Jun on 11/21/2014.
 */
public class SortUnprotectedWordsController {
    Model m;
    ApplicationCanvas panel;
    Board board;

    public static void main(String[] args) {
        Model model = new Model();
        ApplicationCanvas panel = new ApplicationCanvas(model);
        SortUnprotectedWordsController ctr = new SortUnprotectedWordsController(model, panel);
        Word w1 = new Word(10, 20, 20,20, "Test",1);
        Word w5 = new Word(10, 20, 20,20, "Testb",1);
        Word w2 = new Word(10, 20, 20,20, "Aest",1);
        Word w3 = new Word(10, 20, 20,20, "Bat",1);
        Word w6 = new Word(10, 20, 20,20, "bat",1);
        Word w4 = new Word(10, 20, 20,20, "Bet",1);
        ctr.board.getunprotectedWords().add(w1);
        ctr.board.getunprotectedWords().add(w2);
        ctr.board.getunprotectedWords().add(w3);
        ctr.board.getunprotectedWords().add(w4);
        ctr.board.getunprotectedWords().add(w5);
        ctr.board.getunprotectedWords().add(w6);
        System.out.println("unprotected words: " + ctr.board.getunprotectedWords());
        System.out.println("sorted" + ctr.sortByAlphabet(ctr.board.getunprotectedWords()));

    }


    public ArrayList<Word> sortByAlphabet(ArrayList<Word> words){
        TreeMap<String, ArrayList<Word>> tree = new TreeMap<String, ArrayList<Word>>(new MyStringComparator());
        for(Word w : board.getunprotectedWords()){
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
    private class MyStringComparator implements Comparator{
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
                System.out.println(shorter + " is larger than " + longer + " at index " + i + ((shorter.charAt(i) > longer.charAt(i))));
                if(shorter.charAt(i) > longer.charAt(i)){
                    return 1;
                }else if(shorter.charAt(i) < longer.charAt(i)){
                    return -1;
                }
            }
            if(shorter.length() == longer.length()){
                System.out.println(shorter + " equals " + longer);
                return 0;
            }
            System.out.println(shorter + " is smaller " + longer);
            return -1;
        }
    }


    public SortUnprotectedWordsController(Model m, ApplicationCanvas panel) {
        this.m = m;
        this.panel = panel;
        this.board = m.getBoard();
    }

//    public ArrayList<Word> sortByAlphabet(ArrayList<Word> words){
//        return sortByAlphabet(words, 0);
//    }

//    private ArrayList<Word> sortByAlphabet(ArrayList<Word> words, int index){
//        System.out.println("sort alpha index: " + index + " sortByAlphabet.SUWC");
//        DevidedList dl = devide(words, index);
//        sort(dl.empty);
//        for(Object subList : dl.others.values()){
//            System.out.println("others : " + dl.others.values());
//            sortByAlphabet((ArrayList<Word>) subList, index + 1);
//        }
//        combine(dl);
//        return dl.sorted;
////        sortByAlphabet()
////        return true;
//    }

//    private ArrayList<Word> sort(ArrayList<Word> words){
//        if(words.size() < 1){
//            return words;
//        }
//        int wordLength = words.get(0).getValue().length();
//        ArrayList<Word> sorted = new ArrayList<Word>();
//        sorted.add(words.get(0));
//
//        for (int i = 1; i < words.size(); i++){
//            if (words.get(i).getValue().length() != wordLength){
//                throw new InputMismatchException("Words are of different length can not be sorted by this function");
//            }
//            for(int j = 0; j < sorted.size(); j++){
//                if(words.get(j).getValue().charAt(wordLength-1) > sorted.get(j).getValue().charAt(wordLength-1)){
//                    sorted.add(j, words.get(j));
//                    break;
//                }
//                if(j == sorted.size()){
//                    sorted.add(words.get(j));
//                }
//            }
//        }
//        words = sorted;
//        return words;
//
//    }
    /**
     * devide the word list by their char at some index
     * @param words Word arraylist to be sorted
     * @param index the index at which the char of words are to be checked
     * @return
     */
//    private DevidedList devide(ArrayList<Word> words, int index){
//        System.out.println("devide words: " + words + " at index : " +index);
//        DevidedList dl = new DevidedList();
//        for (Word w : words){
//            System.out.println(dl.others);
//            if(w.getValue().length() <= index){
//                dl.empty.add(w);
//            }else{
//                System.out.println("word value: " + w + " :devide.SUWC");
//                if(dl.others.contains(w.getValue().charAt(index))){ // char already exists, put the word in to the arraylist associated with the key
//                    ((ArrayList<Word>)dl.others.get(
//                            w.getValue().charAt(index)
//                    )).add(w);
//                }else { //char not found yet, add a new arraylist
//                    ArrayList<Word> more = new ArrayList<Word>();
//                    more.add(w);
//                    dl.others.put(w.getValue().charAt(index), more);
//                }
//            }
//        }
//        return dl;
//    }

//    private boolean combine(DevidedList<Word> dl){
////        dl.sorted.clear();
//        dl.sorted.addAll(dl.empty);
//        ArrayList<Character> sortedKeys = new ArrayList<Character>();
////        LinkedList<Word> sortedKeys = new LinkedList<Word>();
//        for(Character key : dl.others.keySet()){
//            for(int i = 0; i < sortedKeys.size(); i ++){
//                if(key > sortedKeys.get(i)){
//                    sortedKeys.add(i, key);
//                    break;
//                }
//            }
//        }
//        for(Character key : sortedKeys){
//            dl.sorted.addAll(dl.others.get(key));
//        }
//        return true;
//    }

//    private ArrayList<Word> sort(ArrayList<Word> words, int index){
//        ArrayList<Word> sorted = new ArrayList<Word>();
//        DevidedList dl = devide(words, index);
//        if(dl.empty != null){
//            sorted.addAll(dl.empty);
//        }
//        for(Object subList : dl.others.values()){
////            sortByAlphabet()
//        }
//    }

//    private class DevidedList<T>{
//        ArrayList<T> empty;
////        ArrayList<ArrayList<T>> others;
//        Hashtable<Character, ArrayList<Word>> others;
//
//        ArrayList<T> sorted;
//        public DevidedList(){
//            empty = new ArrayList<T>();
//            others = new Hashtable<Character, ArrayList<Word>>();
//            sorted = new ArrayList<T>();
//        }
////        private DevidedList(ArrayList<T> empty, ArrayList<ArrayList<T>> others) {
////            this.empty = empty;
////            this.others = others;
////        }
//    }
//
//    public boolean sortByType(){
//
//        return true;
//    }
}

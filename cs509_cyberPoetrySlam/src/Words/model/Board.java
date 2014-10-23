package Words.model;
import java.util.*;
public class Board implements Iterable<Word>{
    ArrayList<Word> words = new ArrayList<Word>();
    ArrayList<Word> protectedWords = new ArrayList<Word>();
    ArrayList<Word> unProtectedWords = new ArrayList<Word>();
    //HashSet<String,Word> protectedWords = new HashSet<Word.value,Word>();
    public void restore(BoardMemento m) {
		words = new ArrayList<Word>();
		for (Word s : m.stored) {
			words.add(new Word(s.x, s.y, s.width, s.height, s.value,s.wordType));
		}
	}
	
    public BoardMemento getState() {
		return new BoardMemento(words);
	}
    
	//add words to the board
	public void addWords(Word w){
		words.add(w);
	}
	
	public void protectWords(Word w){
		unProtectedWords.remove(w);
		protectedWords.add(w);
	}
	
	public void releaseWords(Word w){
		protectedWords.remove(w);
		unProtectedWords.add(w);
	}
	
	public Word findWord(int x,int y){
		for(Word w:words){
			if(w.intersection(x,y)){
				return w;
			}
		}
		return null;
	}
	
	
	
	public Iterator<Word> iterator() {
		// TODO Auto-generated method stub
		return words.iterator();
	}
	
	public Iterator<Word> protectedWordsIterator(){
		return protectedWords.iterator();
	}
	
	public Iterator<Word> unprotectedWordsIterator(){
		return unProtectedWords.iterator();
	}
}

package Words.model;
import java.lang.reflect.Array;
import java.util.*;
public class Board implements Iterable<Word>{
    ArrayList<Word> protectedWords = new ArrayList<Word>();
    ArrayList<Word> unprotectedWords = new ArrayList<Word>();
    ArrayList<Poem> poemList = new ArrayList<Poem>();
    ArrayList<Word> words = new ArrayList<Word>(); // TODO to be deleted ? added by JUN

    //HashSet<String,Word> protectedWords = new HashSet<Word.value,Word>();
    public void restore(BoardMemento m) {
		words = new ArrayList<Word>();
		for (Word s : m.stored) {
			words.add(new Word(s.x, s.y, s.width, s.height, s.value,s.wordType));
		}
	}

    public boolean protect(Word w) {
        // check if word is in unprotected word
        if (!unprotectedWords.contains(w)){
            return false;
        }
        this.unprotectedWords.remove(w);
        this.protectedWords.add(w);
        return true;
    }

    public boolean unprotect(Word w) {
        if(!protectedWords.contains(w)){
           return false;
        }
        this.protectedWords.remove(w);
        this.unprotectedWords.add(w);
        return true;
    }

    public boolean addPoem(Poem p) {
       return this.poemList.add(p);
    }

    public boolean removePoem(Poem p) {
       return this.poemList.remove(p);
    }

    // getter and setter
    public void setPoemList(ArrayList<Poem> pl) {
        this.poemList = pl;
    }

    public ArrayList<Poem> getPoemList() {
        return this.poemList;
    }

    public void setprotectedWords(ArrayList<Word> pw) {
        this.protectedWords = pw;
    }

    public ArrayList<Word> getprotectedWords() {
        return this.protectedWords;
    }

    public void setUnprotectedWords(ArrayList<Word> upw) {
        this.unprotectedWords = upw;
    }

    public ArrayList<Word> getUnprotectedWords() {
        return this.unprotectedWords;
    }

    public BoardMemento getState() {
		return new BoardMemento(words);
	}
    
	//add words to the board
	public void addWords(Word w){
		words.add(w);
	}
	
	public void protectWords(Word w){
		this.unprotectedWords.remove(w);
		this.protectedWords.add(w);
	}
	
	public void releaseWords(Word w){
		this.protectedWords.remove(w);
		this.unprotectedWords.add(w);
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
		return this.protectedWords.iterator();
	}
	
	public Iterator<Word> unprotectedWordssIterator(){
		return this.unprotectedWords.iterator();
	}
}

package Words.model;
import java.lang.reflect.Array;
import java.util.*;
public class Board implements Iterable<Word>{
    ArrayList<Word> protectedWord = new ArrayList<Word>();
    ArrayList<Word> unprotectedWord = new ArrayList<Word>();
    ArrayList<Poem> poemList = new ArrayList<Poem>();

    //HashSet<String,Word> protectedWords = new HashSet<Word.value,Word>();
    public void restore(BoardMemento m) {
		words = new ArrayList<Word>();
		for (Word s : m.stored) {
			words.add(new Word(s.x, s.y, s.width, s.height, s.value,s.wordType));
		}
	}

    public boolean protect(Word w) {
        // check if word is in unprotected word
        if (!unprotectedWord.contains(w)){
            return false;
        }
        this.unprotectedWord.remove(w);
        this.protectedWord.add(w);
        return true;
    }

    public boolean unprotect(Word w) {
        if(!protectedWord.contains(w)){
           return false;
        }
        this.protectedWord.remove(w);
        this.unprotectedWord.add(w);
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

    public void setProtectedWord(ArrayList<Word> pw) {
        this.protectedWord = pw;
    }

    public ArrayList<Word> getProtectedWord() {
        return this.protectedWord;
    }

    public void setUnprotectedWord(ArrayList<Word> upw) {
        this.unprotectedWord = upw;
    }

    public ArrayList<Word> getUnprotectedWord() {
        return this.unprotectedWord;
    }

    public BoardMemento getState() {
		return new BoardMemento(words);
	}
    
	//add words to the board
	public void addWords(Word w){
		words.add(w);
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

}

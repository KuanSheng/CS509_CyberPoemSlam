package Words.model;
import java.util.*;
public class Board implements Iterable<Word>{
    ArrayList<Word> protectedWord = new ArrayList<Word>();
    ArrayList<Word> unprotectedWord = new ArrayList<Word>();
    ArrayList<Poem> poemList = new ArrayList<Poem>();
    ArrayList<Row> rowList = new ArrayList<Row>();

    //HashSet<String,Word> protectedWords = new HashSet<Word.value,Word>();
    public void restore(BoardMemento m) {
		words = new ArrayList<Word>();
		for (Word s : m.stored) {
			words.add(new Word(s.x, s.y, s.width, s.height, s.value,s.wordType));
		}
	}

    public void protect(Word w) {
        // check if word is in unprotected word
        if (unprotectedWord.contains(w)){
            this.unprotectedWord.remove(w);
            this.protectedWord.add(w);
        }
    }

    public void unprotect(Word w) {
        if(protectedWord.contains(w)){
            this.protectedWord.remove(w);
            this.unprotectedWord.add(w);
        }
    }

	public void connect(Poem p, Row r, Word w){
         
    }

    public void disconnect() {

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

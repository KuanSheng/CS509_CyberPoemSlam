package Words.model;
import java.util.*;
public class Board implements Iterable<Word>{
    ArrayList<Word> words = new ArrayList<Word>();
    ArrayList<Word> protectedWords = new ArrayList<Word>();
    ArrayList<Word> unProtectedWords = new ArrayList<Word>();
    ArrayList<Poem> poems = new ArrayList<Poem>();
    
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
	
	public void addPoems(Poem p){
		poems.add(p);
	}
	
	public ArrayList<Word> getWords(){
		return this.words;
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
	
	public Poem findPoem(int x,int y){
		for(Poem p:poems){
			if(p.intersection(x,y)){
				return p;
			}
		}
		return null;
	}
	
	public Word checkOverlap(Word w){
		for(Word s:words){
			if(s.equals(w)){
				continue;
			}
			if(s.overlap(w)){
				return s;
			}
		}
		return null;
	}
	
	public boolean checkOverlapPoem(Poem p){
		for(Word s:words){
			for(Row r:p.getRows()){
				if(s.overlapRow(r)){
					return true;
				}
			}
		}
		return false;
	}
	
	public Poem checkOverlapWord(Word w){
		for(Poem p:poems){
			for(Row r:p.getRows()){
				if(w.overlapRow(r)){
					return p;
				}
			}
		}
		return null;
	}

	public int getOverlapType(Word w1,Word w2){
	if((w1.x+w1.width)>w2.getX()&&(w1.x+w1.width)<(w2.getX()+w2.width)&&(w2.getY()+w2.getHeight())>w1.y&&(w2.getY()+w2.getHeight())<(w1.y+w1.height)){
   		 return 1;
   	 }
   	 if((w1.x+w1.width)>w2.getX()&&(w1.x+w1.width)<(w2.getX()+w2.width)&&(w1.y+w1.height)>w2.getY()&&(w1.y+w1.height)<(w2.getY()+w2.height)){
   		 return 2; 
   	 }
   	 if((w2.getX()+w2.getWidth())>w1.x&&(w2.getX()+w2.getWidth())<(w1.x+w1.width)&&(w1.y+w1.height)>w2.getY()&&(w1.y+w1.height)<(w2.getY()+w2.height)){
   		 return 3;
   	 }
   	 if((w2.getX()+w2.getWidth()>w1.x)&&(w2.getX()+w2.getWidth())<(w1.x+w1.width)&&(w2.getY()+w2.getHeight()>w1.y)&&(w2.getY()+w2.getHeight())<(w1.y+w1.height)){
   		 return 4; 
   	 }
   	 if((w2.getX()+w2.getWidth()>w1.x)&&(w2.getX()+w2.getWidth()<w1.x+w1.width)&&w2.getY()==w1.y){
   		 return 5;
   	 }
   	 if((w1.x+w1.width>w2.getX())&&(w1.x+w1.width<w2.getX()+w2.getWidth())&&w1.y==w2.getY()){
   		 return 6;
   	 }
		return 0;
	}
	
	public int getOverlapTypeRowWord(Word w, Row r){
		if(w.getX()+w.getWidth()>r.getX()&&w.getX()+w.getWidth()<r.getX()+r.getWidth()&&
				w.getX()<r.getX()&&w.getY()+w.getHeight()>r.getY()&&w.getY()+w.getHeight()<r.getY()+r.getHeight()){
			return 1;
		}
		if(w.getX()<r.getX()+r.getWidth()&&w.getX()+w.getWidth()>r.getX()+r.getWidth()&&r.getX()<w.getX()
				&&w.getY()+w.getHeight()>r.getY()&&w.getY()+w.getHeight()<r.getY()+r.getHeight()){
			return 2;
		}
		if(w.getX()+w.getWidth()>r.getX()+r.getHeight()&&w.getX()<r.getX()+r.getWidth()&&r.getX()<w.getX()
				&&w.getY()+w.getHeight()>r.getY()+r.getHeight()&&w.getY()<r.getY()+r.getHeight()){
			return 3;
		}
		if(w.getX()+w.getWidth()>r.getX()&&w.getX()+w.getWidth()<r.getX()+r.getWidth()&&w.getX()<r.getX()
				&&w.getY()+w.getHeight()>r.getY()+r.getHeight()&&w.getY()<r.getY()+r.getHeight()){
			return 4;
		}
		if(w.getX()+w.getWidth()>r.getX()&&w.getX()+w.getWidth()<r.getX()+r.getWidth()&&
				w.getX()<r.getX()&&w.getY()==r.getY()){
			return 5;
		}
		if(w.getX()<r.getX()+r.getWidth()&&w.getX()+w.getWidth()>r.getX()+r.getWidth()&&r.getX()<w.getX()
				&&w.getY()==r.getY()){
			return 6;
		}
		if(w.getX()>r.getX()&&w.getX()+w.getWidth()<r.getX()+r.getWidth()&&w.getY()+w.getHeight()>r.getY()&&w.getY()+w.getHeight()<r.getY()+r.getHeight()){
			return 7;
		}
		if(w.getX()>r.getX()&&w.getX()+w.getWidth()<r.getX()&&w.getY()+w.getHeight()>r.getY()+r.getHeight()&&w.getY()<r.getY()+r.getHeight()){
			return 8;
		}
		return 0;
	}
	
	public int getOverlapPoemWord(Poem p, Word w){
		for(Row r:p.getRows()){
			if(w.overlapRow(r)){
				return getOverlapTypeRowWord(w,r);
			}
		}
		return 0;
	}
	
	public Iterator<Poem> poemIterator(){
		return poems.iterator();
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

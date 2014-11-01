package Words.model;
import java.lang.reflect.Array;
import java.util.*;

public class Board implements Iterable<Word>{
    ArrayList<Word> words = new ArrayList<Word>();
    ArrayList<Word> protectedWords = new ArrayList<Word>();
    ArrayList<Word> unProtectedWords = new ArrayList<Word>();
    ArrayList<Poem> poems = new ArrayList<Poem>();

    //HashSet<String,Word> protectedWords = new HashSet<Word.value,Word>();

    public void restore(BoardMemento m) {
		words = new ArrayList<Word>();
		for (Word s : m.stored) {
			words.add(new Word(s.x, s.y, s.width, s.height, s.value,s.wordType));
		}
	}

    public boolean protect(Word w) {
        // check if word is in unprotected word
        if (!unProtectedWords.contains(w)){
            return false;
        }
        this.unProtectedWords.remove(w);
        this.protectedWords.add(w);
        return true;
    }

    public boolean unprotect(Word w) {
        if(!protectedWords.contains(w)){
           return false;
        }
        this.protectedWords.remove(w);
        this.unProtectedWords.add(w);
        return true;
    }


    // getter and setter
    public void setPoemList(ArrayList<Poem> pl) {
        this.poems = pl;
    }

    public ArrayList<Poem> getPoemList() {
        return this.poems;
    }

    public ArrayList<Word> getWords() {
        return this.words;
    }

    public void setProtectedWords(ArrayList<Word> pw) {
        this.protectedWords = pw;
    }

    public ArrayList<Word> getProtectedWords() {
        return this.protectedWords;
    }

    public void setunProtectedWords(ArrayList<Word> upw) {
        this.unProtectedWords = upw;
    }

    public ArrayList<Word> getunProtectedWords() {
        return this.unProtectedWords;
    }

    public BoardMemento getState() {
		return new BoardMemento(words);
	}
    
	//add words to the board
	public void addWords(Word w){
		words.add(w);
		unProtectedWords.add(w);
	}
	
	public void addPoems(Poem p){
		poems.add(p);
	}

    public boolean removePoem(Poem p) {
        return this.poems.remove(p);
    }

    public void protectWords(Word w){
		this.unProtectedWords.remove(w);
		this.protectedWords.add(w);
	}
	
	public void releaseWords(Word w){
		this.protectedWords.remove(w);
		this.unProtectedWords.add(w);
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
		for(Poem poem:poems){
			if(!p.equals(poem)){
			for(Row r:poem.getRows())
				for(Word s:r.getWords())
				   for(Row row:p.getRows()){
					if(s.overlapRow(row)){
						return true;
					}
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
    public int getOverLapNumber(Word w){
    	int number = 0;
    	for(Word s:words){
    		if(w.overlap(s)){
    			number++;
    		}
    	}
    	for(Poem p:poems){
    		for(Row r:p.getRows()){
    			if(w.overlapRow(r)){
    				number++;
    			}
    		}
    	}
    	return number;
    }
    public boolean checkPotentialOverlapPoem(Word selectedWord,Poem connectPoem,int type){
    	Row r = connectPoem.getOverlapRow(selectedWord);
    	if(type == 1){
    		int testx = r.getX()-selectedWord.getWidth();
    		int testy = r.getY();
    		Word test = new Word(testx,testy,selectedWord.getWidth(),selectedWord.getHeight(),"test",2);
    		for(Word w:words){
    			  if(test.overlap(w)){
    				  if(!w.equals(selectedWord))
    				     return true;
    			   }
    		}
    		for(Poem p:poems){
    			for(Row row:p.getRows()){
    				if(test.overlapRow(row)){
    					System.out.println("error!");
    					return true;
    				}
    			}
    		}
    	}
    	else if(type ==2){
    		int testx = r.getX()+r.getWidth()+selectedWord.getWidth();
    		int testy = r.getY();
    		Word test = new Word(testx,testy,selectedWord.getWidth(),selectedWord.getHeight(),"test",2);
    		for(Word w:words){
    			if(test.overlap(w)){
    				return true;
    			}
    		}
    		for(Poem p:poems){
    			for(Row row:p.getRows()){
    				if(test.overlapRow(row)){
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    
    public boolean checkPotentialOverlap(Word selectedWord,Word connectWord,int type){
    	if(type == 1){
    		int testx = connectWord.getX()-selectedWord.getWidth();
    		int testy = connectWord.getY();
    		Word test = new Word(testx,testy,selectedWord.getWidth(),selectedWord.getHeight(),"test",2);
    		for(Word w:words){
    			if(!w.equals(selectedWord)){
    			if(test.overlap(w)){
    				return true;
    			}
    			}
    		}
    		for(Poem p:poems){
    			for(Row r:p.getRows()){
    				if(test.overlapRow(r)){
    					return true;
    				}
    			}
    		}
    	}
    	else if(type == 2){
    		int testx = connectWord.getX()+connectWord.getWidth();
    		int testy = connectWord.getY();
    		Word test = new Word(testx,testy,selectedWord.getWidth(),selectedWord.getHeight(),"test",2);
    		for(Word w:words){
    			if(test.overlap(w)){
    				if(!w.equals(selectedWord)){
    				System.out.println("wo kao");
    				return true;}
    			}
    		}
    		for(Poem p:poems){
    			for(Row r:p.getRows()){
    				if(test.overlapRow(r)){
    					return true;
    				}
    			}
    		}
    	}
    	return false;
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
		if(w.getX()+w.getWidth()>r.getX()+r.getWidth()&&w.getX()<r.getX()+r.getWidth()&&r.getX()<w.getX()
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
		if(w.getX()>r.getX()&&w.getX()+w.getWidth()<r.getX()+r.getWidth()&&w.getY()+w.getHeight()>r.getY()+r.getHeight()&&w.getY()<r.getY()+r.getHeight()){
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
		return this.protectedWords.iterator();
	}
	
	public Iterator<Word> unProtectedWordsIterator(){
		return this.unProtectedWords.iterator();
	}
}

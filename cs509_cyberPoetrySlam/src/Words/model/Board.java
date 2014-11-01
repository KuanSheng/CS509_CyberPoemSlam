package Words.model;
import java.util.*;
public class Board implements Iterable<Word>, java.io.Serializable {
    ArrayList<Word> words = new ArrayList<Word>();
    ArrayList<Word> protectedWords = new ArrayList<Word>();
    ArrayList<Word> unProtectedWords = new ArrayList<Word>();
    ArrayList<Poem> poems = new ArrayList<Poem>();
 
    
	public void addWords(Word w){
		words.add(w);
		unProtectedWords.add(w);
	}
	
	public void addPoems(Poem p){
		poems.add(p);
		
	}
	
	public ArrayList<Word> getWords(){
		return this.words;
	}
	
	public ArrayList<Word> getProtectedWords(){
		return this.protectedWords;
	}
	
	public ArrayList<Word> getunprotectedWords(){
		return this.unProtectedWords;
	}

    public ArrayList<Poem> getPoems() {return this.poems;}
	
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
	public void setPoemList(ArrayList<Poem> poems){
		this.poems = poems;
	}
	
	public void setProtectedWords(ArrayList<Word> protectedW){
		this.protectedWords = protectedW;
	}
	
	public void setunProtectedWords(ArrayList<Word> words){
		this.unProtectedWords = words;
	}
	public void removePoem(Poem p){
		poems.remove(p);
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


    public String toString(){
        String board = new String();
        for(Word w : words){
//            board.concat(w.toString() + " , ");
            board = board + w.toString() + " , ";
        }
        return board;
    }
}
//>>>>>>> refs/remotes/origin/Kuan_Jun

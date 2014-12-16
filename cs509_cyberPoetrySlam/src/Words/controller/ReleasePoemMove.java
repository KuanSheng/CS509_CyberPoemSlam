package Words.controller;

import Words.model.*;
import Words.view.ApplicationCanvas;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Jun on 11/11/2014.
 */
public class ReleasePoemMove extends Move {
    Model m;
    ApplicationCanvas panel;
    Board board;
    Poem poemReleased;
    ArrayList<Row> rowsReleased;
    ArrayList<Word> wordsReleased;

    ArrayList<Integer> xRows;
    ArrayList<Integer> yRows;
    int xInitial;
    int yInitial;

    public ReleasePoemMove(Model m, ApplicationCanvas panel) {
        this.m = m;
        this.panel = panel;
        this.board = m.getBoard();
        poemReleased = m.getSelectedPoem();
        xInitial = poemReleased.getX();
        yInitial = poemReleased.getY();
        Iterator<Row> rowsToRelease = poemReleased.getRows().iterator();
    }

    @Override
    public boolean execute() {
        System.out.println("removing poem from board : execute.ReleasePoemMove");
        board.removePoem(poemReleased);
        System.out.println("adding words to unprotected area: execute.ReleasePoemMove");
        Iterator<Row> itRow = poemReleased.getRows().iterator();
        while (itRow.hasNext()){
            Row r = itRow.next();
            setReleaseLocation(r);
            Iterator<Word> itWord = r.iterator();
            while (itWord.hasNext()){
                Word w = itWord.next();
//                setReleaseLocation(w);
                board.getunprotectedWords().add(w);
                board.getWords().add(w);
            }
//            board.getunprotectedWords().addAll(itRow.next().getWords());
        }
        m.setSelectedPoem(null);
        panel.repaint();
        return true;
    }
    
    @Override
    public boolean redo(){
    	 System.out.println("removing poem from board : execute.ReleasePoemMove");
         board.removePoem(poemReleased);
         System.out.println("adding words to unprotected area: execute.ReleasePoemMove");
         Iterator<Row> itRow = poemReleased.getRows().iterator();
         while (itRow.hasNext()){
             Row r = itRow.next();
             setReleaseLocation(r);
             Iterator<Word> itWord = r.iterator();
             while (itWord.hasNext()){
                 Word w = itWord.next();
//                 setReleaseLocation(w);
                 board.getunprotectedWords().add(w);
                 board.getWords().add(w);
             }
//             board.getunprotectedWords().addAll(itRow.next().getWords());
         }
         m.setSelectedPoem(null);
         panel.repaint();
         return true;
    }
    //set the new location for released words
    public Word setReleaseLocation(Word w){
//        w.setLocation(w.getX(), panel.getWidth()-w.getHeight());
        w.setLocation(w.getX(), w.getY()+panel.getY()); // todo change to dynamic (whole panel is 490 wide)
        return w;
    }
    public Row setReleaseLocation(Row r){
        System.out.println("panel height: " + panel.getWidth() +"r.getY: " + r.getY()+ " sum: " + (r.getY()+panel.getHeight()) + " :setReleaseLocation.ReleasePoemMove");
        r.setLocation(r.getX(),r.getY()+panel.getWidth(),r.getX(),r.getY());
        return r;
    }

    @Override
    public boolean undo() {

        board.addPoems(poemReleased);
        poemReleased.setLocation(poemReleased.getX(),poemReleased.getY(),poemReleased.getRows().get(0).getWords().get(0).getX(),
                            poemReleased.getRows().get(0).getWords().get(0).getY());
//                poemReleased.getY());
        System.out.println("added poem back:" + board.getPoems() + "undo.ReleasePoemMove");
        Iterator<Row> it = poemReleased.getRows().iterator();
        while (it.hasNext()){
            Row r = it.next();
            board.getunprotectedWords().removeAll(r.getWords());
            board.getWords().removeAll(r.getWords());
        }
        System.out.println("location of unprotected words: " + m.getBoard().getPoems().get(0).getRows().get(0).getWords().get(0).getX() + " " + m.getBoard().getPoems().get(0).getRows().get(0).getWords().get(0).getY());
        panel.repaint(); // newly added 11182014
        return true;
    }
}
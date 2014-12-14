package Words.controller;

import Words.model.*;
import Words.view.ApplicationCanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Jun on 11/11/2014.
 */
public class ReleasePoemController implements ActionListener {
    Model m;
    ApplicationCanvas panel;
    Board board;


    public ReleasePoemController(Model m, ApplicationCanvas panel) {
        this.m = m;
        this.panel = panel;
        this.board = m.getBoard();
    }

    public boolean releasePoem(){
        Poem poemToRelease = m.getSelectedPoem();
        if(poemToRelease == null){
            return false;
        }else {
            System.out.println("relasing poem: releasePoem.ReleasePoemController");
            ReleasePoemMove release = new ReleasePoemMove(m, panel);
            release.execute();
            m.getMoves().push(release);
            System.out.println("unprotected words: " + m.getBoard().getunprotectedWords() + "releasePoem.ReleasePoemControler");
            System.out.println("location of unprotected words: " + m.getBoard().getunprotectedWords().get(0).getX() + " " + m.getBoard().getunprotectedWords().get(0).getY());
            return true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("heard action on 'Release' : actionPerformed.ReleasePoemController");
        if (e != null) { //todo need to check which type of event ? (right click, left click?)
            releasePoem();
        }
    }
}
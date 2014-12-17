package Words.controller;

import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.view.ApplicationCanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Enumeration;


/**
 * This controller submits a poem by writing it into a txt file and a serializable file
 * Created by Jun on 11/18/2014.
 */
public class SubmitPoemController implements ActionListener{
    String poemName = "Cyber Poem of Team Callisto";
    Model m;
    ApplicationCanvas panel;
    Board board;
    public enum Method {EMAIL, SER, TXT, ALL};

    Method method = Method.ALL;



    public SubmitPoemController(Model m, ApplicationCanvas panel) {
        this.m = m;
        this.panel = panel;
        this.board = m.getBoard();
    }

    public SubmitPoemController(Model m, ApplicationCanvas panel, Method mth) {
        this.m = m;
        this.panel = panel;
        this.board = m.getBoard();
        this.method = mth;
    }

    //set the method to submit the poem
    public boolean setMethod(Method m){
        for(Method e : Method.values()){
            if(e.equals(m)){
                break;
            }
            return false; //if what passed in is not in the enum of methods return flase;
        }
        this.method = m;
        return true;

    }

    /**
     * submit poem according to "method"--default is ALL (email, serilizable and txt)
     * @return true if successful
     */
    public boolean submitPoem(){
        //m.setSelectedPoem(board.getPoems().get(0)); //todo to be deleted after successfully set selected poems
        switch (method){
            case ALL:
                return writePoemTXT(m.getSubmittedPoem()) && writePoemSer(m.getSubmittedPoem());
            case SER:
                return writePoemSer(m.getSubmittedPoem());
            case TXT:
                return writePoemTXT(m.getSubmittedPoem());
            default:
                System.out.println("ERROR: unknown submittion method: submitPoem. SubmitPoemContrller");
                return false;
        }


    }

    /**
     * write poem into FS as a serilizable object
     * @param p Poem to be submitted
     * @return true if successful
     */
    private boolean writePoemSer(Poem p){
        try {
            File poem = new File(System.getProperty("user.dir")+"/temp/poem.ser");
            if(!poem.exists()){
                poem = new File(System.getProperty("user.dir")+"/temp/poem.ser");
                poem.getParentFile().mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(poem);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(p);
            out.close();
            fileOut.close();
            System.out.println("written out to FS: wPTFS. SPC");
        }catch (IOException i){
            i.printStackTrace();
        }
        return true;
    }

    /**
     * write poem as a txt file into directory temp/poem.txt
     * @param p the poem to be bulished
     * @return true if successful
     */
    private boolean writePoemTXT(Poem p){
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter( new FileWriter( System.getProperty("user.dir")+"/temp/poem.txt"));
            writer.write(poemName+ "\n" +p.toString());
            writer.close();
            return true;
        }
        catch ( IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if ( writer != null)
                    writer.close( );
            }
            catch ( IOException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("action heard on 'submit button' submitting: actionPerformed. SubmitPoemController");
        submitPoem();
    }
}

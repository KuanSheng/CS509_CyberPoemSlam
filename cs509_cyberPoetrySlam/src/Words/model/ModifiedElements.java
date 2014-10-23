package Words.model;

import java.util.ArrayList;

/**
 * Created by Jun on 10/22/2014.
 */
public class ModifiedElements {
    ArrayList<Element> destroyed;
    ArrayList<Element> generated;

    public ModifiedElements(ArrayList<Element> d, ArrayList<Element> g){
        destroyed = d;
        generated = g;
    }

    public ArrayList<Element> getDestroyed() {
        return destroyed;
    }

    public ArrayList<Element> getGenerated() {
        return generated;
    }
}

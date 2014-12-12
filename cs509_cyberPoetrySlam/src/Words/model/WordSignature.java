package Words.model;

import java.io.Serializable;

/**
 * Created by Jun on 12/11/2014.
 */
public class WordSignature implements Serializable {
    String value;
    String type;
    int typeInt;
    public WordSignature(String value, String type){
        this.value = value;
        this.type = type;
    }

    public WordSignature(String value, int typeInt){
        this.value = value;
        this.typeInt = typeInt;
//        this.type =
    }

}

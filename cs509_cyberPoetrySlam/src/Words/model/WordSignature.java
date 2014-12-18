package Words.model;

import java.io.Serializable;

/**
 * the class used to store information about initial words
 * Created by Jun on 12/11/2014.
 */
public class WordSignature implements Serializable {
    public String getType() {
        return type;
    }


    public String getValue() {
        return value;
    }

    String value;
    String type;
    public WordSignature(String value, String type) {
        this.value = value;
        this.type = type;
    }
}
package org.interpreter;

import java.util.HashMap;
import java.util.Map;

public class Interpreter {
    public Map<String, Integer> hashID;

    public Interpreter(){
        this.hashID = new HashMap<>();
    }

    public boolean addID(String variable, String reference) {
        int value;
        
        if(reference.matches("^[a-z,A-Z]+[0-9]*")){
            value = getID(reference);
            if(value == -1)
                return false;
        } else
            value = Integer.parseInt(reference);
        
        this.hashID.put(variable, value);
        return true;
    }

    public int getID(String reference) {
        return this.hashID.getOrDefault(reference, -1);
    }


    
}

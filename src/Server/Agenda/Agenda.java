package Server.Agenda;

// FILE2: Agenda

import Server.Interfaces.IRepository;
import java.util.Hashtable;

public class Agenda implements IRepository {
    private final Hashtable<String, Integer> ht = new Hashtable<>();

    public void write(String s, int v) {
// Assigns a value d to a string key
        ht.put(s, v);
    }

    public int read(String s) {
// Returns the value of key
        return ht.get(s);
    }
}
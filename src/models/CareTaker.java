package models;

import java.io.Serializable;
import java.util.ArrayList;

public class CareTaker implements Serializable {
    public void addMemento(Moments m) {
        states.add(m);
    }
    public Moments getMemento(int index) {
        return states.get(index);
    }

    private ArrayList<Moments> states = new ArrayList();
    public ArrayList<Moments> getStates() {
        return states;
    }
}

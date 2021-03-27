package com.company;

import java.util.ArrayList;

public class Library {
    public ArrayList<PrintE> printE = new ArrayList();
    public Library(){
    }
    public void add(PrintE PrintE){
        this.printE.add(PrintE);
    }
    public int getCount() {
        return this.printE.size();
    }

    public PrintE getPapers(int index) {
        return printE.get(index);
    }

    public void remove(int ind) {
        this.printE.remove(ind);
    }

    public PrintE set(int id, PrintE printe){
        return printE.set(id,printe);
    }
}

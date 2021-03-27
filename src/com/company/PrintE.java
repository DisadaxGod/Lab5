package com.company;

public abstract class PrintE {

    private static int count = 1;
    protected int id;
    protected String authorName;
    protected String Name;
    protected int pages;

    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }

    public PrintE(){
        this.id = count;
        count++;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
}

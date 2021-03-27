package com.company;

public class TextBook extends PrintE {

    protected String Subject;
    public TextBook(String author, String name, String subject , int pages){
        this.authorName = author;
        this.Name = name;
        this.Subject = subject;
        this.pages = pages;
    }
    public String getSubject() {
        return this.Subject;
    }
    public void setSubject(String Subject) {
        this.Subject = Subject;
    }
}

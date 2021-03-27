package com.company;

public class Magazine extends PrintE {
    protected String Genre;

    public String getGenre() {
        return this.Genre;
    }
    public void setGenre(String Genre) {
        this.Genre = Genre;
    }
    public Magazine(String author, String name, String genre , int pages){
        this.authorName = author;
        this.Name = name;
        this.Genre = genre;
        this.pages = pages;
    }
}
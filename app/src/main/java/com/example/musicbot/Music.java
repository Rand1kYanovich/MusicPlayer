package com.example.musicbot;



public class Music {

    private String title;
    private String artist;

    public Music(String title,String aritst){

        this.title = title;
        this.artist = aritst;
    }

    public String getTitle(){
        return this.title;
    }

    public String getArtist(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

}

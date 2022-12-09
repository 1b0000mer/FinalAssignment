package com.liepu.finalassignment.assignment.models;

import androidx.annotation.NonNull;

public class Card {
    String suit;
    String value, url;
    boolean isJoker, isTouch = false;

    public Card() { }

    public Card(String suit, String value, boolean isJoker, boolean isTouch, String url) {
        this.suit = suit;
        this.value = value;
        this.isJoker = isJoker;
        this.isTouch = isTouch;
        this.url = url;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public boolean isJoker() {
        return isJoker;
    }

    public boolean isTouch() {
        return isTouch;
    }

    public String getUrl() { return url;}

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setJoker(boolean joker) {
        this.isJoker = joker;
    }

    public void setUrl(String url) {
       this.url = url;
    }

    public void setTouch(boolean touch) {
        isTouch = touch;
    }
}

package com.example.set;

import java.util.ArrayList;

public class Request {
    String action;
    String nickname;
    int token;
    ArrayList<Card> cards;

    public Request(String action, String nickname) {
        this.action = action;
        this.nickname = nickname;
    }

    public Request(String action, int token) {
        this.action = action;
        this.token = token;
    }

    public Request(String action, int token, ArrayList<Card> cards) {
        this.action = action;
        this.token = token;
        this.cards = cards;
    }
}

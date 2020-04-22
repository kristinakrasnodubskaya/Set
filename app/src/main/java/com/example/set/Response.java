package com.example.set;

import java.util.ArrayList;

class Response {
    String status;
    int token;
    ArrayList<Card> cards;
    int cards_left;
    int points;

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", token=" + token +
                ", cards=" + cards +
                ", cards_left=" + cards_left +
                ", point=" + points +
                '}';
    }
}

package com.example.set;


public class Card {
    int count, fill, shape, color;

    public Card(int count, int fill, int shape, int color) {
        this.count = count;
        this.fill = fill;
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Card{" +
                "count=" + count +
                ", fill=" + fill +
                ", shape=" + shape +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (fill != card.fill) return false;
        if (count != card.count) return false;
        if (shape != card.shape) return false;
        return color == card.color;

    }

    @Override
    public int hashCode() {
        int result = fill;
        result = 31 * result + count;
        result = 31 * result + shape;
        result = 31 * result + color;
        return result;
    }

    private int OneTwoORThree(int i, int j) {
        if ((i == Math.abs(i - j)) || (j == Math.abs(i - j))){
            return (i + j);}
        else return Math.abs(i - j);
    }

    public int thirdValue(int i, int j) {
        if (i == j)
            return i;
        else return OneTwoORThree(i, j);
    }

    public Card getThird(Card c) {
        Card card3 = new Card(this.count, this.fill, this.shape, this.color);
        card3.count = thirdValue(this.count, c.count);
        card3.fill = thirdValue(this.fill, c.fill);
        card3.shape = thirdValue(this.shape, c.shape);
        card3.color = thirdValue(this.color, c.color);

        return card3;
    }
}

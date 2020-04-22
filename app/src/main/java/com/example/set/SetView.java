package com.example.set;

import android.content.Context;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


public class SetView extends View {
    int token;
    String player;
    ArrayList<Card> cards;
    int cards_left;
    int points;
    TextView card_left;
    TextView point;
    ArrayList<CardView> cardsview = new ArrayList<>();
    ArrayList<Card> findset = new ArrayList<Card>();

    int chosenCard = 0;
    boolean init_field = false;
    int k1 = 4, k2 = 3; // 4x3
    Card firstCard, secondCard, thirdCard;

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public SetView(Context context) {
        super(context);
    }

    public SetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void deleteSetFromDesk() {
        ArrayList<CardView> deletefromDesk = new ArrayList<>();
        for (int i = 0; i < findset.size(); i++) {
            for (int j = 0; j < cardsview.size(); j++) {
                if (findset.get(i).count == cardsview.get(j).count && findset.get(i).fill == cardsview.get(j).fill &&
                        findset.get(i).shape == cardsview.get(j).shape && findset.get(i).color == cardsview.get(j).color) {
                    deletefromDesk.add(cardsview.get(j));
                }
            }
        }
        cardsview.removeAll(deletefromDesk);
        findset.clear();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if (!init_field) {
            int a = (int) (Math.min(width, height) * 0.8 / 3); //length
            int b = (int) (Math.max(width, height) * 0.8 / 4); //height
            int offset = (int) (Math.min(width, height) * 0.1 / 4); //offset_between
            int f1 = (int) ((Math.min(width, height) - a * k2 - offset * (k2 - 1)) / 2); //offset_for_center
            int f2 = (int) ((Math.max(width, height) - b * k1 - offset * (k1 - 1)) / 2); //offset_for_center

            for (int i = 0; i < cards.size(); i++) {
                cardsview.add(new CardView(cards.get(i).count, cards.get(i).fill, cards.get(i).shape, cards.get(i).color));
            }

            for (int i = 0; i < k1; i++) {
                for (int j = 0; j < k2; j++) {
                    cardsview.get(j + i + i * 2).x = (a + offset) * j + f1;
                    cardsview.get(j + i + i * 2).y = (b + offset) * i + f2;
                    cardsview.get(j + i + i * 2).width = a;
                    cardsview.get(j + i + i * 2).height = b;
                }
            }
            init_field = true;
        }

        if (cards.size() > 0) {
            for (CardView c : cardsview) {
                c.draw(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (int i = 0; i < cards.size(); i++) {
                if (cardsview.get(i).clickOnCard(x, y)) {
                    if (chosenCard == 0) {
                        chosenCard++;
                        firstCard = cards.get(i);
                        invalidate();
                        return true;
                    } else if (chosenCard == 1) {
                        chosenCard++;
                        secondCard = cards.get(i);
                        thirdCard = cards.get(i).getThird(firstCard);
                        invalidate();
                        return true;
                    } else if (chosenCard == 2) {
                        if (cards.get(i).equals(thirdCard)) {
                            findset.add(firstCard);
                            findset.add(secondCard);
                            findset.add(thirdCard);
                            Request req = new Request("take_set", token, findset);
                            ReqCheckSet reg = new ReqCheckSet();
                            reg.execute(req);
                        }
                        chosenCard = 0;
                    }
                }
            }
        }
        return true;
    }


    class ReqCheckSet extends AsyncTask<Request, Void, Response> {
        @Override
        protected Response doInBackground(Request... requests) {
            Gson gson = new Gson();
            String set_server_url = "http://194.176.114.21:8054";
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(set_server_url);
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoOutput(true);
                OutputStream out = urlConnection.getOutputStream();
                out.write(gson.toJson(requests[0]).getBytes());

                InputStream stream = urlConnection.getInputStream();
                Response checkset = gson.fromJson(new InputStreamReader(stream), Response.class);
                return checkset;
            } catch (IOException e) {
                return null;
            } finally {
                urlConnection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(Response ans) {
            super.onPostExecute(ans);
            if (ans.status.equals("ok")) {
                cards_left = ans.cards_left;
                points = ans.points;
                cards.removeAll(findset);

                deleteSetFromDesk();
                invalidate();
            }
        }
    }
}

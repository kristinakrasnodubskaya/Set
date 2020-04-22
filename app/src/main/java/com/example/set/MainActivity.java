package com.example.set;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText name;
    SetView set_game;
    int token;
    String nickname;
    ArrayList<Card> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.igrok);
        sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
    }

    public void registration(View view) {
        nickname = name.getText().toString();
        if (sharedPreferences.contains(nickname)) {
            token = sharedPreferences.getInt(nickname, 0);
            try {
                Request req = new Request("fetch_cards", token);
                ReqGetCards reg = new ReqGetCards();
                reg.execute(req);
            } catch (Exception e) {
            }
        } else {
            // вызвать AsynkTask запроса
            try {
                if (!nickname.equals("")) {
                    Request req = new Request("register", nickname);
                    ReqRegist reg = new ReqRegist();
                    reg.execute(req);
                }
            } catch (Exception e) {
            }
        }
    }


    class ReqRegist extends AsyncTask<Request, Void, Response> {
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
                Response registration = gson.fromJson(new InputStreamReader(stream), Response.class);
                return registration;
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
                token = ans.token;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(nickname, ans.token);
                editor.apply();

                Request req = new Request("fetch_cards", token);
                ReqGetCards reg = new ReqGetCards();
                reg.execute(req);
            }
        }
    }


    class ReqGetCards extends AsyncTask<Request, Void, Response> {
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
                Response getcards = gson.fromJson(new InputStreamReader(stream), Response.class);
                return getcards;
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
                cards = ans.cards;

                setContentView(R.layout.activity_set);
                set_game = findViewById(R.id.setview);
                set_game.setPlayer(nickname);
                set_game.setToken(token);
                set_game.setCards(cards);
            }
        }
    }
}

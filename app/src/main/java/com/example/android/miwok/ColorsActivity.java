package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;


public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<WordWithImage> words = new ArrayList<>();

        words.add(new WordWithImage("wetetti", "red",R.drawable.color_red));
        words.add(new WordWithImage("chokokki", "green",R.drawable.color_green));
        words.add(new WordWithImage("takaakki", "brown",R.drawable.color_brown));
        words.add(new WordWithImage("topoppi", "gray",R.drawable.color_gray));
        words.add(new WordWithImage("kululli", "black",R.drawable.color_black));
        words.add(new WordWithImage("kelelli", "white",R.drawable.color_white));
        words.add(new WordWithImage("topiisa", "dusty yellow",R.drawable.color_dusty_yellow));
        words.add(new WordWithImage("chiwiita", "mustard yellow",R.drawable.color_mustard_yellow));

        WordWithImageAdapter itemsAdapter = new WordWithImageAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }
}

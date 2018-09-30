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
        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("wetetti", "red",R.raw.color_red,R.drawable.color_red));
        words.add(new Word("chokokki", "green",R.raw.color_green,R.drawable.color_green));
        words.add(new Word("takaakki", "brown",R.raw.color_brown,R.drawable.color_brown));
        words.add(new Word("topoppi", "gray",R.raw.color_gray,R.drawable.color_gray));
        words.add(new Word("kululli", "black",R.raw.color_black,R.drawable.color_black));
        words.add(new Word("kelelli", "white",R.raw.color_white,R.drawable.color_white));
        words.add(new Word("topiisa", "dusty yellow",R.raw.color_dusty_yellow,R.drawable.color_dusty_yellow));
        words.add(new Word("chiwiita", "mustard yellow",R.raw.color_mustard_yellow,R.drawable.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(this, words,getResources().getColor(R.color.category_colors));
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setBackgroundColor(getResources().getColor(R.color.tan_background));
    }
}

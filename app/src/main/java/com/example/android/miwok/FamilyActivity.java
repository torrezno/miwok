package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;


public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<WordWithImage> words = new ArrayList<>();

        words.add(new WordWithImage("әpә", "father",R.drawable.family_father));
        words.add(new WordWithImage("әṭa", "mother",R.drawable.family_mother));
        words.add(new WordWithImage("angsi", "son",R.drawable.family_son));
        words.add(new WordWithImage("tune", "daughter",R.drawable.family_daughter));
        words.add(new WordWithImage("taachi", "older brother",R.drawable.family_older_brother));
        words.add(new WordWithImage("chalitti", "younger brother",R.drawable.family_younger_brother));
        words.add(new WordWithImage("teṭe", "older sister",R.drawable.family_older_sister));
        words.add(new WordWithImage("kolliti", "younger sister",R.drawable.family_younger_sister));
        words.add(new WordWithImage("ama", "grandmother",R.drawable.family_grandmother));
        words.add(new WordWithImage("paapa", "grandfather",R.drawable.family_grandfather));

        WordWithImageAdapter itemsAdapter = new WordWithImageAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }
}
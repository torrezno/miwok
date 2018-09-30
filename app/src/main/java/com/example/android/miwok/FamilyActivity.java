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
        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("әpә", "father",R.raw.family_father,R.drawable.family_father));
        words.add(new Word("әṭa", "mother",R.raw.family_mother,R.drawable.family_mother));
        words.add(new Word("angsi", "son",R.raw.family_son,R.drawable.family_son));
        words.add(new Word("tune", "daughter",R.raw.family_daughter,R.drawable.family_daughter));
        words.add(new Word("taachi", "older brother",R.raw.family_older_brother,R.drawable.family_older_brother));
        words.add(new Word("chalitti", "younger brother",R.raw.family_younger_brother,R.drawable.family_younger_brother));
        words.add(new Word("teṭe", "older sister",R.raw.family_older_sister,R.drawable.family_older_sister));
        words.add(new Word("kolliti", "younger sister",R.raw.family_younger_sister,R.drawable.family_younger_sister));
        words.add(new Word("ama", "grandmother",R.raw.family_grandmother,R.drawable.family_grandmother));
        words.add(new Word("paapa", "grandfather",R.raw.family_grandfather,R.drawable.family_grandfather));

        WordAdapter itemsAdapter = new WordAdapter(this, words, getResources().getColor(R.color.category_family));
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }
}
package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<WordWithImage> words = new ArrayList<>();
        //String[] words = new String[10];
        //Regex to replace
        //  1         2  3    4   5
        // (words\[)(.*)(\]=)(.*)(;)
        // words.add($4);
        //(words.add\()(.*\")(;)
        //$1$2);

        words.add(new WordWithImage("lutti", "one", R.drawable.number_one));
        words.add(new WordWithImage("otiiko", "two", R.drawable.number_two));
        words.add(new WordWithImage("tolookosu", "three", R.drawable.number_three));
        words.add(new WordWithImage("oyyisa", "four", R.drawable.number_four));
        words.add(new WordWithImage("massokka", "five", R.drawable.number_five));
        words.add(new WordWithImage("temmokka", "six", R.drawable.number_six));
        words.add(new WordWithImage("kenekaku", "seven", R.drawable.number_seven));
        words.add(new WordWithImage("kawinta", "eight", R.drawable.number_eight));
        words.add(new WordWithImage("wo'e", "nine", R.drawable.number_nine));
        words.add(new WordWithImage("na'aacha", "ten", R.drawable.number_ten));

        WordWithImageAdapter itemsAdapter = new WordWithImageAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);


/*        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        int index=0;
        while(index<words.size()){
            TextView wordView = new TextView(this);
            wordView.setText(words.get(index));
            rootView.addView(wordView);
            index++;
        }
        for (int index = 0; index < words.size(); index++) {
            TextView wordView = new TextView(this);
            wordView.setText(words.get(index));
            rootView.addView(wordView);
        }
*/

    }
}

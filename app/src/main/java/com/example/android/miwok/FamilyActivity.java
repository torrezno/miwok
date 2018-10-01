package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;


public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        final ArrayList<Word> words = new ArrayList<>();

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this,words.get(i).getSound());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
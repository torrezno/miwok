package com.example.android.miwok;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int i) {
                    switch (i){
                        case AudioManager.AUDIOFOCUS_GAIN:
                        case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                        case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE:
                        case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                            mMediaPlayer.start();
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            mMediaPlayer.pause();
                            mMediaPlayer.seekTo(0);
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS:
                            mMediaPlayer.stop();
                            releaseMediaPlayer();
                            break;
                        default:
                            break;
                    }
                }
            };

    private AudioManager am;// = (AudioManager) getSystemService(AudioManager.class);

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        am = (AudioManager) getSystemService(AudioManager.class);
        final ArrayList<Word> words = new ArrayList<>();
        //String[] words = new String[10];
        //Regex to replace
        //  1         2  3    4   5
        // (words\[)(.*)(\]=)(.*)(;)
        // words.add($4);
        //(words.add\()(.*\")(;)
        //$1$2);

        words.add(new Word("lutti", "one", R.raw.number_one, R.drawable.number_one));
        words.add(new Word("otiiko", "two", R.raw.number_two, R.drawable.number_two));
        words.add(new Word("tolookosu", "three", R.raw.number_three, R.drawable.number_three));
        words.add(new Word("oyyisa", "four", R.raw.number_four, R.drawable.number_four));
        words.add(new Word("massokka", "five", R.raw.number_five, R.drawable.number_five));
        words.add(new Word("temmokka", "six", R.raw.number_six, R.drawable.number_six));
        words.add(new Word("kenekaku", "seven", R.raw.number_seven, R.drawable.number_seven));
        words.add(new Word("kawinta", "eight", R.raw.number_eight, R.drawable.number_eight));
        words.add(new Word("wo'e", "nine", R.raw.number_nine, R.drawable.number_nine));
        words.add(new Word("na'aacha", "ten", R.raw.number_ten, R.drawable.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(this, words,getResources().getColor(R.color.category_numbers));
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                int result =  am.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(i).getSound());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

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
            am.abandonAudioFocus(audioFocusChangeListener);
        }
    }
}

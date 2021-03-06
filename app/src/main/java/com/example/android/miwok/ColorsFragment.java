package com.example.android.miwok;


import android.annotation.TargetApi;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

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





    public ColorsFragment() {
        // Required empty public constructor
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        am = (AudioManager) getActivity().getSystemService(AudioManager.class);
        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("wetetti", "red",R.raw.color_red,R.drawable.color_red));
        words.add(new Word("chokokki", "green",R.raw.color_green,R.drawable.color_green));
        words.add(new Word("takaakki", "brown",R.raw.color_brown,R.drawable.color_brown));
        words.add(new Word("topoppi", "gray",R.raw.color_gray,R.drawable.color_gray));
        words.add(new Word("kululli", "black",R.raw.color_black,R.drawable.color_black));
        words.add(new Word("kelelli", "white",R.raw.color_white,R.drawable.color_white));
        words.add(new Word("topiisa", "dusty yellow",R.raw.color_dusty_yellow,R.drawable.color_dusty_yellow));
        words.add(new Word("chiwiita", "mustard yellow",R.raw.color_mustard_yellow,R.drawable.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words,getResources().getColor(R.color.category_colors));
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setBackgroundColor(getResources().getColor(R.color.tan_background));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                int result =  am.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(getActivity(),words.get(i).getSound());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
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

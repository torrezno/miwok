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
public class FamilyFragment extends Fragment {

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

    private AudioManager am;// = this.getSystemService(AudioManager.class);


    public FamilyFragment() {
        // Required empty public constructor
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container,false);
        am = (AudioManager) getActivity().getSystemService(AudioManager.class);
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

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words,getResources().getColor(R.color.category_family));
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                int result =  am.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), words.get(i).getSound());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }
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

package com.example.android.miwok;

public class Word {
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mSound;
    private int mImageId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;


    public Word(String miwokTranslation, String defaultTranslation) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
    }

    public Word(String miwokTranslation, String defaultTranslation, int sound, int imageId) {
        this(miwokTranslation,defaultTranslation);
        this.mImageId = imageId;
        this.mSound = sound;
    }
    public Word(String miwokTranslation, String defaultTranslation, int imageId) {
        this(miwokTranslation,defaultTranslation);
        this.mImageId = imageId;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getmImageId() {
        return mImageId;
    }

    public int getSound() {
        return mSound;
    }
    public boolean hasAnImage(){
        return mImageId != NO_IMAGE_PROVIDED;
    }
}

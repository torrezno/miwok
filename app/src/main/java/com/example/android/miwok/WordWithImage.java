package com.example.android.miwok;

public class WordWithImage extends Word {
    private int mImgId;

    public WordWithImage(String miwokTranslation, String defaultTranslation, int imgId) {
        super(miwokTranslation, defaultTranslation);
        mImgId = imgId;
    }

    public int getImgId() {
        return mImgId;
    }
}

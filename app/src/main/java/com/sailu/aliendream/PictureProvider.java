package com.sailu.aliendream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;

public abstract class PictureProvider {

    public class PictureData {
        public Bitmap bitmap;
        public String description;

        public PictureData() {
            bitmap = null;
            description = null;
        }

        public PictureData(Bitmap bitmap, String description) {
            this.bitmap = bitmap;
            this.description = description;
        }
    }

    private final String TAG = "PictureProvider";
    protected PictureData m_nextPicture = null;

    protected abstract PictureData prepareNextPicture();

    public void cacheNextPicture() {
        m_nextPicture = prepareNextPicture();
    }
    public PictureData getNextPicture() {
        return m_nextPicture;
    }

    public void clearCache() {
        m_nextPicture = null;
    }

    protected Bitmap downloadBitmap(String url) {
        Log.i(TAG, "downloadBitmap: " + url);
        Bitmap bitmap;
        try {
            InputStream in = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
}

package com.sailu.aliendream;

import android.os.Handler;
import android.util.Log;

import com.sailu.aliendream.providers.SimpleProvider;

import java.util.TimerTask;

public class PictureRotater extends TimerTask {
    private static final String TAG = "PictureRotater";
    private boolean m_dreaming = false;
    private FrameDisplay m_frameDisplay;
    private PictureProvider m_pictureProvider;

    public PictureRotater(FrameDisplay display) {
        m_frameDisplay = display;
    }

    @Override
    public void run() {
        Log.i(TAG, "Running rotation");
        if (!m_dreaming) {
            return;
        }

        PictureProvider.PictureData pictureData = m_pictureProvider.getNextPicture();
        if (pictureData == null) {
            // Either we are just starting the screensaver or previous get failed
            // Try again
            m_pictureProvider.cacheNextPicture();
            pictureData = m_pictureProvider.getNextPicture();
        }

        if (pictureData != null) {
            m_frameDisplay.changePicture(pictureData);
        }

        scheduleRotation(2000);
        m_pictureProvider.cacheNextPicture();
    }

    private void scheduleRotation(int millis) {
        final Handler handler = new Handler();
        handler.postDelayed(this, millis);
    }

    public void startRotation() {
        m_dreaming = true;
        m_pictureProvider = new SimpleProvider();
        scheduleRotation(0);
    }

    public void stopRotation() {
        m_dreaming = false;
    }
}

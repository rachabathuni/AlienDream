package com.sailu.aliendream;

import android.os.StrictMode;
import android.service.dreams.DreamService;

import java.util.TimerTask;
import android.os.Handler;
import android.util.Log;


public class AlienDream extends DreamService {
    private final String TAG = "AlienDream";
    private FrameDisplay m_frameDisplay;
    private PictureRotater m_pictureRotater;

    private void setThreadPolicy() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onAttachedToWindow() {
        Log.i(TAG,"onAttachedtoWindow");
        super.onAttachedToWindow();
        setFullscreen(true);

        setThreadPolicy();

        m_frameDisplay = new FrameDisplay(this);
        setContentView(m_frameDisplay.getView());
        m_pictureRotater = new PictureRotater(m_frameDisplay);
    }

    /* DreamService */
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void onDreamingStarted() {
        Log.i(TAG, "onDreamingStarted");
        super.onDreamingStarted();
        m_pictureRotater.startRotation();
    }

    public void onDreamingStopped() {
        super.onDreamingStopped();
        m_pictureRotater.stopRotation();
    }
}

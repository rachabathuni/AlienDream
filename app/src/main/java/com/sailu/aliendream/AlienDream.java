package com.sailu.aliendream;

import android.os.StrictMode;
import android.service.dreams.DreamService;

import java.util.TimerTask;
import android.os.Handler;


public class AlienDream extends DreamService {
    private FrameDisplay m_frameDisplay;
    private PictureRotater m_pictureRotater;

    private void setThreadPolicy() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onAttachedToWindow() {
        System.out.println("srdebug: onAttachedToWindow");
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
        System.out.println("srdebug: onDreamingStarted");
        super.onDreamingStarted();
        m_pictureRotater.startRotation();
    }

    public void onDreamingStopped() {
        //videoController.stop();
        super.onDreamingStopped();
        m_pictureRotater.stopRotation();
    }
}

package com.sailu.aliendream;

import android.os.Handler;

import java.util.TimerTask;

public class PictureRotater extends TimerTask {

    private boolean m_dreaming = false;
    private FrameDisplay m_frameDisplay;

    public PictureRotater(FrameDisplay display) {
        m_frameDisplay = display;
    }

    @Override
    public void run() {
        if (!m_dreaming) {
            return;
        }

        m_frameDisplay.rotatePicture();
        scheduleRotation(2000);
    }

    private void scheduleRotation(int millis) {
        final Handler handler = new Handler();
        handler.postDelayed(this, millis);
    }

    public void startRotation() {
        m_dreaming = true;
        scheduleRotation(0);
    }

    public void stopRotation() {
        m_dreaming = false;
    }
}

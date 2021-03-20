package com.sailu.aliendream;

import android.service.dreams.DreamService;

public class AlienDream extends DreamService {
    private VideoController videoController;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setFullscreen(true);

        videoController = new VideoController(this);
        setContentView(videoController.getView());
    }

    /* DreamService */
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void onDreamingStarted() {
        super.onDreamingStarted();
    }

    public void onDreamingStopped() {
        videoController.stop();
        super.onDreamingStopped();
    }
}

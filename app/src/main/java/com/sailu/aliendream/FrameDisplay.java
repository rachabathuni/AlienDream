package com.sailu.aliendream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.DataBindingUtil;

import com.sailu.aliendream.databinding.AlienDreamBinding;

import java.io.InputStream;

import static android.widget.ImageView.ScaleType.FIT_CENTER;

public class FrameDisplay {
    private static final String TAG = "FrameDisplay";
    private AlienDreamBinding m_binding;
    private Context m_context;

    public FrameDisplay(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        m_binding = DataBindingUtil.inflate(inflater, R.layout.alien_dream, null, false);
        m_context = context;
        m_binding.imageView.setScaleType(FIT_CENTER);
    }

    public View getView() {
        return m_binding.getRoot();
    }

    private String getTimeString() {
        return "10:10";
    }

    public void changePicture(PictureProvider.PictureData pictureData) {
        System.out.println("srdebug: PictureController.rotatePicture");
        Log.i(TAG, "changePicture");

        Animation fadeout = AnimationUtils.loadAnimation(m_context, R.anim.fadeout);
        m_binding.imageView.startAnimation(fadeout);

        int viewWidth = m_binding.container.getWidth();
        int viewHeight = m_binding.container.getHeight();
        int imageWidth = pictureData.bitmap.getWidth();
        int imageHeight = pictureData.bitmap.getHeight();

        // Prepare a target bitmap to show to display. Set the color to black
        Log.d(TAG, "viewWidth: " + viewWidth + ", viewHeight: " + viewHeight);
        Bitmap targetBitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
        targetBitmap.eraseColor(Color.BLACK);

        // Create a canvas with backing target image to put texton
        Canvas targetCanvas = new Canvas(targetBitmap);

        // Draw the given bitmap on target canvas
        targetCanvas.drawBitmap(pictureData.bitmap, new Rect(0,0, imageWidth, imageHeight), new Rect(0,0,viewWidth,viewHeight), null);

        // Add time to target canvas
        String time = getTimeString();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(viewHeight/16);
        Rect textBounds = new Rect();
        paint.getTextBounds(time, 0, time.length(), textBounds);
        int time_y = viewHeight-textBounds.height()*2;
        targetCanvas.drawText(time, viewWidth- textBounds.width()*2, time_y, paint);

        paint.setTextSize(viewHeight/32);
        String desc;
        if (pictureData.description.length() < 48) {
            desc = pictureData.description;
        } else {
            desc = pictureData.description.substring(0, 48);
        }

        targetCanvas.drawText(desc, 30, time_y, paint);

        Animation fadein = AnimationUtils.loadAnimation(m_context, R.anim.fadein);
        m_binding.imageView.startAnimation(fadein);
        m_binding.imageView.setImageBitmap(targetBitmap);
    }
}

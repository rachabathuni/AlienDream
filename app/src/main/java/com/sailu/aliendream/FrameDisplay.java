package com.sailu.aliendream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.DataBindingUtil;

import com.sailu.aliendream.databinding.AlienDreamBinding;

import java.io.InputStream;

import static android.widget.ImageView.ScaleType.FIT_CENTER;

public class FrameDisplay {
    private AlienDreamBinding m_binding;
    private Context m_context;
    private int m_index = 0;
    private final String m_pictures[] = {
            "http://www.nwhiker.com/wallpaper/wp01.jpg",
            "http://www.nwhiker.com/wallpaper/wp02.jpg",
            "http://www.nwhiker.com/wallpaper/wp03.jpg",
            "http://www.nwhiker.com/wallpaper/wp04.jpg",
            "http://www.nwhiker.com/wallpaper/wp05.jpg",
            "http://www.nwhiker.com/wallpaper/wp06.jpg",
            "http://www.nwhiker.com/wallpaper/wp07.jpg",
            "http://www.nwhiker.com/wallpaper/wp08.jpg",
            "http://www.nwhiker.com/wallpaper/wp09.jpg",
            "http://www.nwhiker.com/wallpaper/wp20.jpg"
    };

    public FrameDisplay(Context context) {
        System.out.println("srdebug: PictureController.constructor");
        LayoutInflater inflater = LayoutInflater.from(context);
        m_binding = DataBindingUtil.inflate(inflater, R.layout.alien_dream, null, false);
        m_context = context;
        m_binding.imageView.setScaleType(FIT_CENTER);
    }

    public View getView() {
        System.out.println("srdebug: PictureController.getView");
        return m_binding.getRoot();
    }

    public void rotatePicture() {
        System.out.println("srdebug: PictureController.rotatePicture");

        Animation fadeout = AnimationUtils.loadAnimation(m_context, R.anim.fadeout);
        m_binding.imageView.startAnimation(fadeout);

        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(m_pictures[m_index]).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            //Log.e("Error", e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

        int viewWidth = m_binding.container.getWidth();
        int viewHeight = m_binding.container.getHeight();
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();

        System.out.println("srdebug: viewWidth: " + viewWidth + ", viewHeight: " + viewHeight);
        Bitmap targetBitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
        targetBitmap.eraseColor(Color.BLACK);
        Canvas targetCanvas = new Canvas(targetBitmap);
        targetCanvas.drawBitmap(bitmap, new Rect(0,0, imageWidth, imageHeight), new Rect(0,0,viewWidth,viewHeight), null);


        String time = "Hi Siddy";
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(viewHeight/16);
        int textHeight = viewHeight/16;
        Rect textBounds = new Rect();
        paint.getTextBounds(time, 0, time.length(), textBounds);
        targetCanvas.drawText(time, viewWidth- textBounds.width()*2, viewHeight-textBounds.height()*2, paint);


        Animation fadein = AnimationUtils.loadAnimation(m_context, R.anim.fadein);

        //binding.imageView.setImageResource(0);
        //binding.imageView.setImageAlpha(10);
        m_binding.imageView.startAnimation(fadein);
        m_binding.imageView.setImageBitmap(targetBitmap);


        /*
        for (int i=255; i>0; i--) {
            binding.imageView.setImageAlpha(i);
        }

        Glide.with(this.m_context).clear(binding.imageView);
        Glide.with(this.m_context)
                .load(pictures[index])
                .into(binding.imageView);

        for (int i=0; i<=255; i++) {
            binding.imageView.setImageAlpha(i);
        }
        */
        m_index = (m_index + 1)  % m_pictures.length;
    }

}

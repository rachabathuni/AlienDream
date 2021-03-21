package com.sailu.aliendream.providers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.sailu.aliendream.PictureProvider;

import java.io.InputStream;

public class SimpleProvider extends PictureProvider {
    private static final String TAG = "SimpleProvider";

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
    private int m_index = 0;

    @Override
    protected PictureProvider.PictureData prepareNextPicture() {
        Bitmap bitmap = downloadBitmap(m_pictures[m_index]);
        String description = m_pictures[m_index];
        m_index = (m_index+1) % m_pictures.length;
        Log.d(TAG, "m_index=" + m_index);

        if (bitmap == null) {
            return null;
        }

        PictureData pd = new PictureData(bitmap, description);
        return pd;
    }
}

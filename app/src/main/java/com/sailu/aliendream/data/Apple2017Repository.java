package com.sailu.aliendream.data;

import android.content.Context;

import com.sailu.aliendream.R;

import java.util.List;

public class Apple2017Repository extends VideoRepository {
    @Override
    public List<Apple2017Video> fetchVideos(Context context) {
        Wrapper wrapper = parseJson(context, R.raw.tvos11, Wrapper.class);
        return wrapper.assets;
    }

    private static class Wrapper {
        private List<Apple2017Video> assets;
    }
}

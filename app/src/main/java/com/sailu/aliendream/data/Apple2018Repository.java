package com.sailu.aliendream.data;

import android.content.Context;

import com.sailu.aliendream.R;

import java.util.List;

public class Apple2018Repository extends VideoRepository {
    @Override
    public List<Apple2018Video> fetchVideos(Context context) {
        Wrapper wrapper = parseJson(context, R.raw.tvos12, Wrapper.class);
        return wrapper.assets;
    }

    private static class Wrapper {
        private List<Apple2018Video> assets;
    }
}

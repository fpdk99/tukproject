package com.tuk.tukar;

        import android.app.Application;

        import com.mapbox.mapboxsdk.Mapbox;
        import com.mapbox.vision.VisionManager;

public class TukAR extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VisionManager.init(this, getResources().getString(R.string.mapbox_access_token));
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
    }
}
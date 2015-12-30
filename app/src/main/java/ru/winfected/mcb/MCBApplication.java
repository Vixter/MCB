package ru.winfected.mcb;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by winfe on 30.12.2015.
 */
public class MCBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this.getApplicationContext());
    }
}

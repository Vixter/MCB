package ru.winfected.mcb;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.facebook.drawee.backends.pipeline.Fresco;

import ru.winfected.mcb.network.themoviedb.Params;

/**
 * Created by winfe on 30.12.2015.
 */
public class MCBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this.getApplicationContext());
        Params.API_KEY_VALUE = getString(R.string.themoviedb_api_key);
    }

    public static boolean isConnectingToInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}

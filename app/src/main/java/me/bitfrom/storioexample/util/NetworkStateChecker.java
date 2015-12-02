package me.bitfrom.storioexample.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Helper class for checking internet connection
 */
public class NetworkStateChecker {

    /**
     * Returns true if the network is available or about become available.
     * @param context used to get the ConnectivityManager
     * @return boolean statement
     * **/
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}

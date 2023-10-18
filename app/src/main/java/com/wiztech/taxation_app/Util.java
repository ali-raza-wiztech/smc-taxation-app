package com.wiztech.taxation_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;

public class Util {
    public static ProgressDialog pDialog;

    public static void displayLoader(Context context, String title){
        if (context != null) {
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("" + title);
            pDialog.setIndeterminate(false);
            pDialog.show();
        }


    }
    public static void dismissLoader(){
        if (pDialog != null) {
            pDialog.dismiss();
        }
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null;
        }
        return false;
    }
}

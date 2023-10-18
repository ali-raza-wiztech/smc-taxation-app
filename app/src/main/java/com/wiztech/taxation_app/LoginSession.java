package com.wiztech.taxation_app;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.wiztech.taxation_app.Entities.User;
import com.wiztech.taxation_app.databinding.ActivityLoginBinding;
import com.wiztech.taxation_app.databinding.ActivityMainBinding;

public class LoginSession {
    public static final String LOGGED_IN_PREF = "logged_in_status";
    public static final String token = "token";


    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static void setUserData(Context context, User user) {
        SharedPreferences.Editor editor = getPreferences(context).edit();

        editor.putString("userName", user.getUserName());
        editor.putString("email", user.getEmail());
        editor.putString("password", user.getPassword());
        editor.putString("gender", user.getGenders());
        editor.putInt("id", user.getId());
        editor.putString("firstName", user.getFirstName());
        editor.putString("lastName", user.getLastName());
        editor.putString("fullName",user.getFullName());
        editor.putString("cnic", user.getCnic());
        editor.putString("contact", user.getContact());
        editor.putString("token", user.getToken());
        editor.apply();
    }

    public static User getUser(Context context) {
        SharedPreferences sp = getPreferences(context);
        User user = new User();


        user.setUserName(sp.getString("userName", ""));
        user.setId(sp.getInt("id", 0));//pensioner id
        user.setFirstName(sp.getString("firstName", ""));
        user.setLastName(sp.getString("lastName", ""));
        user.setCnic(sp.getString("cnic", ""));
        user.setContact(sp.getString("contact", ""));
        user.setFullName(sp.getString("fullName",""));
        user.setEmail(sp.getString("email", ""));
        user.setPassword(sp.getString("password", ""));
        user.setGenders(sp.getString("gender", ""));
        user.setToken(sp.getString("token",""));

        return user;
    }

    public static String getUserToken(Context context) {
        return getPreferences(context).getString("token", "");
    }

    public static void logout(Context context) {
        setLoggedIn(context, false);
    }

}
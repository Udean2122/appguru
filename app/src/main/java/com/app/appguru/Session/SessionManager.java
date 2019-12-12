package com.app.appguru.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences preference;
    SharedPreferences.Editor editor;
    Context context;

    public static final String PREF_NAME = "LoginSuccess";

    private static final String IS_FISRT_TIME_LAUNCH = "LoginCheck";

    public SessionManager(Context context){
        this.context = context;
        preference = context.getSharedPreferences(PREF_NAME, 0);
        editor = preference.edit();
    }

    public void checkLogin(boolean isLogged){
        editor.putBoolean(IS_FISRT_TIME_LAUNCH, isLogged);
        editor.commit();
    }

    public boolean isLogged(){
        return preference.getBoolean(IS_FISRT_TIME_LAUNCH, false);
    }

}

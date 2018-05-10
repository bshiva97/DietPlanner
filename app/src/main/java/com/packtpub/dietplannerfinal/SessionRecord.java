package com.packtpub.dietplannerfinal;

/**
 * Created by archi on 25-09-2017.
 */
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.w3c.dom.Text;

public class SessionRecord {
    SharedPreferences pref;
    Editor editor;
    Context context;
    private static final String PREF="_pref";

    public static final String us_id="useid";
    public static final String isLogin="isloggedin";

    public SessionRecord(Context context)
    {this.context=context;
        pref = context.getSharedPreferences(PREF, 0);//zero indicates private mode
        editor = pref.edit();


    }
    public void setLogggedIn(boolean login)
    {
        editor.putBoolean(isLogin,login);
        editor.commit();
    }
    public void setId(String id)
    {
        editor.putString(us_id,id);
        editor.commit();
    }
    public boolean isLoggedIn()
    {
        return  pref.getBoolean(isLogin,false);
    }
    public String getUs_id()
    {
        return pref.getString(us_id,"");
    }




}

package com.packtpub.dietplannerfinal;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
public class MainActivity extends Activity {
    String tag="CrawledData";
    int i=0;
    Document doc;
    Element value,headline;
    String news,newslink;
    public static final int not_id=1234;
    Uri uri=null;
    String lastnews=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Handler handler = new Handler();
        Log.d("myname", "This Is Main Activity");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), front.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new DownloadWebPage().execute("");
    }

    private class DownloadWebPage extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (news != null) {
                Log.d("myname", "Connected To Internet");
                long[] vibrations = {250, 250, 500, 250, 250};
                String httpurlstring = "https://timesofindia.indiatimes.com" + newslink;
                Uri uri = Uri.parse(httpurlstring);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
                mBuilder.setSmallIcon(R.mipmap.one);
                mBuilder.setContentTitle(news);
                mBuilder.setDefaults(Notification.DEFAULT_SOUND);
                mBuilder.setVibrate(vibrations);
                mBuilder.setAutoCancel(true);
                mBuilder.setLights(0xff00ff00, 300, 1000);
                Intent intent = new Intent(MainActivity.this, front.class);
                Intent resultIntent = new Intent(Intent.ACTION_VIEW);
                resultIntent.setData(uri);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(pi);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotificationManager.notify(not_id, mBuilder.build());
            }
        }
        @Override
        protected String doInBackground(String... strings) {
         while (!isOnline())
               ;
            String url1 = "https://timesofindia.indiatimes.com/life-style/health-fitness/diet";
            String tip;
            try {
                doc = Jsoup.connect(url1).get();
                value=doc.select("[class=hl_li clearfix]").first();
                headline=value.select("h3").select("a").first();
                newslink=headline.attr("href");
                news=headline.text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public Boolean isOnline() {
        if (networkConnectivity()) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL(
                        "http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(3000);
                urlc.setReadTimeout(4000);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                return (false);
            }
        } else
            return false;
    }

    private boolean networkConnectivity() {
        ConnectivityManager cm = (ConnectivityManager) getBaseContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
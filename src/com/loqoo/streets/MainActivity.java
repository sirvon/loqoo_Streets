package com.loqoo.streets;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        AppEngine.musicThread = new Thread() {
                            public void run() {
                                             Intent bgmusic = new Intent(getApplicationContext(), AppMusic.class);
                                             startService(bgmusic);
                                             AppEngine.context = getApplicationContext();
                                             }
                                            };
        AppEngine.musicThread.start();
  }

  protected void onPause(Bundle savedInstanceState) {
        try
        {
                                             Intent bgmusic = new Intent(getApplicationContext(), AppMusic.class);
                                             stopService(bgmusic);
                                             AppEngine.context = getApplicationContext();
                                             }
                                            };
        AppEngine.musicThread.stop();

  protected void onStop(Bundle savedInstanceState) {
          try
        {
                                             Intent bgmusic = new Intent(getApplicationContext(), AppMusic.class);
                                             stopService(bgmusic);
                                             AppEngine.context = getApplicationContext();
                                             }
                                            };
        AppEngine.musicThread.stop();

  }




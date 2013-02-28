package com.loqoo.streets;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.content.Context;

public class MainActivity extends Activity {


    @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        Button buttonloqooTvList = (Button)findViewById(R.id.loqootvlist);
        buttonloqooTvList.setOnClickListener(new Button.OnClickListener(){
            @Override
              public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoqooTvList.class);
                startActivity(intent);
        }
});

          Button buttonloqooStreets = (Button)findViewById(R.id.towashave_ew_miafl_usa);
          buttonloqooStreets.setOnClickListener(new Button.OnClickListener(){
            @Override
              public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WashAveEWMiaFLUSA.class);
                startActivity(intent);
        }
});

          AppEngine.musicThread = new Thread() {
                            public void run() {
                                          Intent bgmusic = new Intent(getApplicationContext(), AppMusic.class);
                                          startService(bgmusic);
                                          AppEngine.context = getApplicationContext();
                                                     };
                                    };
        AppEngine.musicThread.start();

        }
   
    }





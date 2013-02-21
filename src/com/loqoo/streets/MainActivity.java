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


Button washaveewmiaflusa = (Button)findViewById(R.id.towashave_ew_miafl_usa);

        washaveewmiaflusa.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View view) {
             Intent intent = new Intent(MainActivity.this, WashAveEWMiaFLUSA.class);
               startActivity(intent);
           }
        });


  }

}


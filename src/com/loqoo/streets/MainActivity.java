package com.loqoo.streets;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends Activity {


    @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
      final Button buttonloqooTvList = (Button)findViewById(R.id.loqootvlist);

              buttonloqooTvList.setOnClickListener(new Button.OnClickListener(){
    @Override
      public void onClick(View arg0) {

               Intent intent = new Intent(context, LoqooTvList.class);
                                              context.startActivity(intent);
}

}

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





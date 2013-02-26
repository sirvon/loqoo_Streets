package com.loqoo.streets;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

public class Splash1 extends Activity {

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash1);

      new Handler().postDelayed(new Thread() {
             @Override
            public void run() {
                   Intent mainMenu = new Intent(Splash1.this,
 MainActivity.class);
                    Splash1.this.startActivity(mainMenu);
                    Splash1.this.finish();
                    overridePendingTransition(R.layout.fadein,R.layout.fadeout);
         }
     }, AppEngine.GAME_THREAD_DELAY);

  

  }

}
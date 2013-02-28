package com.loqoo.streets;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.widget.ViewFlipper;
import android.widget.Button;
import android.view.View;

public class Splash1 extends Activity {

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash1);
        final ViewFlipper SplashFlipper = (ViewFlipper)findViewById(R.id.splash_flipper);
        
       Animation animationFlipIn  = AnimationUtils.loadAnimation(this, R.layout.fadein);
       Animation animationFlipOut = AnimationUtils.loadAnimation(this, R.layout.fadeout);
       SplashFlipper.setInAnimation(animationFlipIn);
       SplashFlipper.setOutAnimation(animationFlipOut);

       SplashFlipper.setFlipInterval(AppEngine.SPLASH_TRANSITION_INTERVAL);

       final Button buttonAutoFlip = (Button)findViewById(R.id.test);

       buttonAutoFlip.setOnClickListener(new Button.OnClickListener(){

   @Override
   public void onClick(View arg0) {
    // TODO Auto-generated method stub
    
    if(SplashFlipper.isFlipping()){
     SplashFlipper.stopFlipping();
     buttonAutoFlip.setText("Start Auto Flip");
    }else{
     SplashFlipper.startFlipping();
     buttonAutoFlip.setText("Stop Auto Flip");
    }
    
    
    
   }});

buttonAutoFlip.performClick();

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
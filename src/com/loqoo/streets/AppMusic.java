package com.loqoo.streets;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.media.MediaPlayer;
import android.content.Context;

public class AppMusic extends Service {

             public static boolean isRunning = false;
             MediaPlayer player;

             @Override
                 public IBinder onBind(Intent arg0) {
                            return null;
                 }

             @Override
                 public void onCreate() {
                            super.onCreate();
                     setMusicOptions(this, AppEngine.LOOP_BACKGROUND_MUSIC,AppEngine.R_VOLUME, AppEngine.L_VOLUME, AppEngine.SPLASH_SCREEN_MUSIC);
           }
            public void setMusicOptions(Context context, boolean isLooped, int rVolume, int lVolume,
 int soundFile){
                             player = MediaPlayer.create(context, soundFile);
                             player.setLooping(isLooped);
                             player.setVolume(rVolume,lVolume);
            }
              

                 public int onStartCommand(Intent intent, int flags, int startId) {
                            try                    
                     {
                            player.start();
                            isRunning = true;
                                         }catch(Exception e){
                            isRunning = false;
                            player.stop();
                                         }                                          
                                         return 1;
                     }


                 public void onStart(Intent intent, int startId) {

                 }

                 public void onStop() {
                           isRunning = false;
  
                 }

                 public IBinder onUnBind(Intent arg0) {
             // TODO Auto-generated method stub              
                               return null;
                 }

                 public void onPause() {
                            player.stop();
                            player.release();
                 }             

                @Override
                public void onDestroy() {
                            player.stop();
                            player.release();

                 }                  

                @Override
                public void onLowMemory() {
                            player.stop();
                         

                 }

  } 


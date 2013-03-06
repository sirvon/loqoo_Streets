package com.loqoo.streets;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.loopj.android.http.*;

public class LoqooTvList extends Activity {

AsyncHttpClient httpReq = new AsyncHttpClient(); 

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loqootvlisting);

httpReq.get("https://api.mongolab.com/api/1/databases/sirvon/collections/music?f={%22url%22:%201}&apiKey=wjphi_cqGKEDur5kDZxrCEJYvxSlOpE0", new AsyncHttpResponseHandler() {
  @Override
   public void onSuccess(String response) {
       System.outprintln(response);
   }
});


    
}

}
package com.loqoo.streets;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

import com.loqoo.streets.InternetCheck;

public class LoqooTvList extends Activity {

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loqootvlisting);

        InternetCheck ic = new InternetCheck();
        ic.internetCheckFunc();
      
}

}
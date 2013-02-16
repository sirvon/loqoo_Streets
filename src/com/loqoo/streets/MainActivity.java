package com.loqoo.streets;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;

public class MainActivity extends Activity implements ScrollViewListener {

    private ObservableScrollView scrollView1 = null;
    private ObservableScrollView scrollView2 = null;
    private ObservableScrollView scrollView3 = null;
    private ObservableScrollView scrollView4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        

        scrollView1 = (ObservableScrollView) findViewById(R.id.washav_west_biz);
        scrollView1.setScrollViewListener(this);
        scrollView2 = (ObservableScrollView) findViewById(R.id.washav_east_biz);
        scrollView2.setScrollViewListener(this);
        scrollView3 = (ObservableScrollView) findViewById(R.id.washav_east_sidewalk);
        scrollView3.setScrollViewListener(this);
        scrollView4 = (ObservableScrollView) findViewById(R.id.scrollView4);
        scrollView4.setScrollViewListener(this);

      
    }

    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {

    if(scrollView == scrollView1) {
        scrollView2.scrollTo(x, y);
        scrollView3.scrollTo(x, y);
        scrollView4.scrollTo(x, y);
    } else if(scrollView == scrollView2) {
        scrollView1.scrollTo(x, y);
        scrollView3.scrollTo(x, y);
        scrollView4.scrollTo(x, y);
    } else if(scrollView == scrollView3) {
        scrollView1.scrollTo(x, y);
        scrollView2.scrollTo(x, y);
        scrollView4.scrollTo(x, y);
    } else if(scrollView == scrollView4) {
        scrollView1.scrollTo(x, y);
        scrollView2.scrollTo(x, y);
        scrollView3.scrollTo(x, y);
    }

  }

}


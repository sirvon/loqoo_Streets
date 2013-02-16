package com.loqoo.streets;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;

public class MainActivity extends Activity implements ScrollViewListener {

    private ObservableScrollView scrollView1 = null;
    private ObservableScrollView scrollView2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        

        scrollView1 = (ObservableScrollView) findViewById(R.id.scrollView1);
        scrollView1.setScrollViewListener(this);
        scrollView2 = (ObservableScrollView) findViewById(R.id.scrollView2);
        scrollView2.setScrollViewListener(this);

        scrollView1.fullScroll(ScrollView.FOCUS_DOWN);
    }

    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if(scrollView == scrollView1) {
            scrollView2.scrollTo(x,y);
        } else if(scrollView == scrollView2) {
            scrollView1.scrollTo(x,y);
        }
    }

}


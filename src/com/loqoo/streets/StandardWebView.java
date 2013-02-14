package com.loqoo.streets;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class StandardWebView extends Activity {

    // Declare Variables
    WebView webview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set Title
        setTitle("WebView Tutorial");
        // To show progress bar
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.webview);

        webview = (WebView) findViewById(R.id.webview);

        // Enable Javascript to run in WebView
        webview.getSettings().setJavaScriptEnabled(true);

        // Allow Zoom in/out controls
        webview.getSettings().setBuiltInZoomControls(true);

        // Zoom out the best fit your screen
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);

        // Load URL
        webview.loadUrl("http://www.AndroidBegin.com");

        // Showing the progress bar
        webview.setWebChromeClient(new WebChromeClient() {
				public void onProgressChanged(WebView view, int progress) {
					setProgress(progress * 100);
				}
			});

        // To call private class InsideWebViewClient
        webview.setWebViewClient(new InsideWebViewClient());

    }

    // Force links to be opened inside WebView and not in Default Browser
    private class InsideWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }

    }
}
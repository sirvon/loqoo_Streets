package com.loqoo.streets;
 
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
 
public class CustomWebView extends Activity {
 
    //Declare Variables
    WebView webview;
    String htmlcodes;
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set Title
        setTitle("WebView Tutorial");
        setContentView(R.layout.webview);
        webview = (WebView) findViewById(R.id.webview);
         
        //Custom HTML Codes
        htmlcodes = "<html><body>WebView Tutorial from AndroidBegin.com</body></html>";
         
        //Load the HTML Codes in WebView
        webview.loadData(htmlcodes, "text/html", "UTF-8");

    }
}
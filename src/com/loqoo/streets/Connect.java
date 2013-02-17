package com.loqoo.streets;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;

 
    public String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }        
        return stringBuilder.toString();
    }

private class ReadWeatherJSONFeedTask extends AsyncTask
    <String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }
 
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject weatherObservationItems = 
                    new JSONObject(jsonObject.getString("weatherObservation"));
 
                Toast.makeText(getBaseContext(), 
                    weatherObservationItems.getString("clouds") + 
                 " - " + weatherObservationItems.getString("stationName"), 
                 Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
            }          
        }
    }


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect);
    }
    
    public void btnGetWeather(View view) {
        EditText txtLat = (EditText) findViewById(R.id.txtLat);
        EditText txtLong = (EditText) findViewById(R.id.txtLong);

        new ReadWeatherJSONFeedTask().execute(
            "http://ws.geonames.org/findNearByWeatherJSON?lat=" +
            txtLat.getEditableText().toString() + "&lng=" +
            txtLong.getText().toString());

}
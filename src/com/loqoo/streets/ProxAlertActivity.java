package com.loqoo.streets

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProxAlertActivity extends Activity {
    
    private static final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATE = 1000; // in Milliseconds
    
    private static final long POINT_RADIUS = 1000; // in Meters
    private static final long PROX_ALERT_EXPIRATION = -1; 

    private static final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
    private static final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";
    
    private static final String PROX_ALERT_INTENT = 
         "com.javacodegeeks.android.lbs.ProximityAlert";
    
    private static final NumberFormat nf = new DecimalFormat("##.########");
    
    private LocationManager locationManager;
    
    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private Button findCoordinatesButton;
    private Button savePointButton;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prox);
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 
                        MINIMUM_TIME_BETWEEN_UPDATE, 
                        MINIMUM_DISTANCECHANGE_FOR_UPDATE,
                        new MyLocationListener()
        );
        
        latitudeEditText = (EditText) findViewById(R.id.point_latitude);
        longitudeEditText = (EditText) findViewById(R.id.point_longitude);
        findCoordinatesButton = (Button) findViewById(R.id.find_coordinates_button);
        savePointButton = (Button) findViewById(R.id.save_point_button);
        
        findCoordinatesButton.setOnClickListener(new OnClickListener() {            
            @Override
            public void onClick(View v) {
                populateCoordinatesFromLastKnownLocation();
            }
        });
        
        savePointButton.setOnClickListener(new OnClickListener() {            
            @Override
            public void onClick(View v) {
                saveProximityAlertPoint();
            }
        });
       
    }
    
    private void saveProximityAlertPoint() {
        Location location = 
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location==null) {
            Toast.makeText(this, "No last known location. Aborting...", 
                Toast.LENGTH_LONG).show();
            return;
        }
        saveCoordinatesInPreferences((float)location.getLatitude(),
               (float)location.getLongitude());
        addProximityAlert(location.getLatitude(), location.getLongitude());
    }

    private void addProximityAlert(double latitude, double longitude) {
        
        Intent intent = new Intent(PROX_ALERT_INTENT);
        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        
        locationManager.addProximityAlert(
            latitude, // the latitude of the central point of the alert region
            longitude, // the longitude of the central point of the alert region
            POINT_RADIUS, // the radius of the central point of the alert region, in meters
            PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration 
            proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
       );
        
       IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);  
       registerReceiver(new ProximityIntentReceiver(), filter);
       
    }

    private void populateCoordinatesFromLastKnownLocation() {
        Location location = 
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location!=null) {
            latitudeEditText.setText(nf.format(location.getLatitude()));
            longitudeEditText.setText(nf.format(location.getLongitude()));
        }
    }
    
    private void saveCoordinatesInPreferences(float latitude, float longitude) {
        SharedPreferences prefs = 
           this.getSharedPreferences(getClass().getSimpleName(),
                           Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putFloat(POINT_LATITUDE_KEY, latitude);
        prefsEditor.putFloat(POINT_LONGITUDE_KEY, longitude);
        prefsEditor.commit();
    }
    
    private Location retrievelocationFromPreferences() {
        SharedPreferences prefs = 
           this.getSharedPreferences(getClass().getSimpleName(),
                           Context.MODE_PRIVATE);
        Location location = new Location("POINT_LOCATION");
        location.setLatitude(prefs.getFloat(POINT_LATITUDE_KEY, 0));
        location.setLongitude(prefs.getFloat(POINT_LONGITUDE_KEY, 0));
        return location;
    }
    
    public class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            Location pointLocation = retrievelocationFromPreferences();
            float distance = location.distanceTo(pointLocation);
            Toast.makeText(ProxAlertActivity.this, 
                    "Distance from Point:"+distance, Toast.LENGTH_LONG).show();
        }
        public void onStatusChanged(String s, int i, Bundle b) {            
        }
        public void onProviderDisabled(String s) {
        }
        public void onProviderEnabled(String s) {            
        }
    }
    
}
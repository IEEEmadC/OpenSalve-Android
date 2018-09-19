package com.agzuniverse.agz.opensalve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class HomeActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Mapbox.getInstance(this, getString(R.string.mapbox_api_token));

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {

            }
        });

//        Intent debug = new Intent(this, CollectionCentreScreen.class);
//        this.startActivity(debug);
    }

    public void goToGetHelpScreen(View v) {
        //Go to get help screen
    }

    public void goToGiveHelpScreen(View v) {
        //Go to give help screen
    }
}

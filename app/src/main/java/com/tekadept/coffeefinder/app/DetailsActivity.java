package com.tekadept.coffeefinder.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;
import java.util.Objects;

public class DetailsActivity extends ActionBarActivity {

    private Activity myActivity;
    private CoffeeFinderApplication myApp;
    private GoogleMap mMap;
    private int listingId;
    TextView businessName;
    TextView address;
    TextView city;
    TextView state;
    TextView zip;
    TextView telephone;
    Map<String, Object> shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);


        myActivity = this;
        myApp = (CoffeeFinderApplication) this.getApplication();

        setWidgets();
        setUpMapIfNeeded();

        myActivity = this;
        myApp = (CoffeeFinderApplication) this.getApplication();
    }

    private void setWidgets() {
        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.containsKey(Constants.LISTING_ID)){
            listingId = extras.getInt(Constants.LISTING_ID);
        } else {
            listingId = 0;
        }

        shop = myApp.getListingItem(listingId);
        businessName = (TextView)findViewById(R.id.businessName);
        address = (TextView)findViewById(R.id.address);
        city = (TextView)findViewById(R.id.city);
        state = (TextView)findViewById(R.id.state);
        zip = (TextView)findViewById(R.id.zip);
        telephone = (TextView)findViewById(R.id.telephone);

        businessName.setText(shop.get("businessName").toString());
        address.setText(shop.get("street").toString());
        city.setText(shop.get("city").toString());
        state.setText(shop.get("state").toString());

        int zipCode = (int) Math.round(((Double)shop.get("zip")));

        zip.setText(Integer.toString(zipCode));
        telephone.setText(shop.get("phone").toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_map:
                intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_listings:
                intent = new Intent(this, ListingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        LatLng loc = new LatLng((Double)shop.get("latitude"), (Double)shop.get("longitude"));
        mMap.addMarker(new MarkerOptions()
                .position(loc)
                .title(shop.get("businessName").toString())
                .snippet(shop.get("street").toString()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 14));
    }
}

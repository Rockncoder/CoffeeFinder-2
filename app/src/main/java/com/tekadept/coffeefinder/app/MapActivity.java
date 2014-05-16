package com.tekadept.coffeefinder.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;


public class MapActivity extends FragmentActivity implements Observer, GoogleMap.OnInfoWindowClickListener {

    private Activity myActivity;
    private CoffeeFinderApplication myApp;
    private GoogleMap mMap;
    private Map<Marker, Integer> listingIds = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        myActivity = this;
        myApp = (CoffeeFinderApplication) this.getApplication();
        myApp.getPool().addObserver(this);

        setUpMapIfNeeded();
        setWidgets();

        myActivity = this;
        myApp = (CoffeeFinderApplication) this.getApplication();
        myApp.getPool().addObserver(this);


        if (myApp.hasListings()) {
            update(null, null);
        }
    }

    private void setWidgets() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_listings:
                Log.v(Constants.LOG_TAG, "tapped Listings");

                Intent i = new Intent(myActivity, ListingsActivity.class);
                startActivity(i);

                return true;
            case R.id.action_settings:
                Log.v(Constants.LOG_TAG, "tapped Settings");
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
        LatLng loc = myApp.getLocation();

        mMap.addMarker(new MarkerOptions().position(loc).title("Your Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 14));
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.v(Constants.LOG_TAG, "Map Update CALLED!!!");

        List listings = myApp.getList();

        for (Object item : listings) {
            Map<String, Object> shop = (Map<String, Object>)item;
            Marker m = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng((Double)shop.get("latitude"), (Double)shop.get("longitude")))
                    .title(shop.get("businessName").toString())
                    .snippet(shop.get("street").toString()));

            listingIds.put(m, (int)Math.round(((Double)shop.get("listingId"))));
        }
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (listingIds.containsKey(marker)) {
            int id = listingIds.get(marker);
            Log.v(Constants.LOG_TAG, String.format("listing Id = %d", id));

            Intent intent = new Intent(myActivity, DetailsActivity.class);
            intent.putExtra(Constants.LISTING_ID, id);
            startActivity(intent);
        }
    }
}

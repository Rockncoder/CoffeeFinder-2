package com.tekadept.coffeefinder.app;


import android.app.Application;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.tekadept.coffeefinder.Dtos.MyObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoffeeFinderApplication extends Application  implements
        LocationListener,
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener  {

    public String currentSearchTerm = "coffee";
    public int currentRadius = 5;
    private List listings;
    private ObservablePool pool;
    //    private LatLng mLatLng = new LatLng(33.749464, -118.272114); // Downtown Los Angeles
    private LatLng mLatLng = new LatLng(34.052234, -118.243685); // Middle of the Vincent Thomas Bridge
    private LocationClient mLocationClient;
    public Location mCurrentLocation;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(Constants.LOG_TAG, "In Application onCreate");

        pool = new ObservablePool();

        if(servicesConnected()){
            mLocationClient = new LocationClient(this, this, this);
            mLocationClient.connect();
        }
    }

    private void fetchLocations() {
        pool.addTask(new DownloadSearchResultsTask(), new ArrayList<String>());
        pool.executeTasks();
    }

    private boolean servicesConnected() {
        // Check that Google Play services is available
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d(Constants.LOG_TAG, "Google Play services are available.");
            // Continue
            return true;
            // Google Play services was not available for some reason
        } else {
            Log.d(Constants.LOG_TAG, "Google Play services are NOT available.");
        }
        return false;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.v(Constants.LOG_TAG, "onConnected");

        mCurrentLocation = mLocationClient.getLastLocation();
        if(mCurrentLocation != null) {
            String loc = String.format("Latitude = %5.6f, Longitude = %5.6f", mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
            Log.v(Constants.LOG_TAG, loc);
            mLatLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        }
        fetchLocations();
    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.v(Constants.LOG_TAG, "onConnectionFailed");
        fetchLocations();
    }

    private class DownloadSearchResultsTask extends AsyncTask<String, Void, String> {
        final static String YPSearchURL = "http://pubapi.atti.com/search-api/search/devapi/search?searchloc=%s&term=%s&format=json&sort=distance&radius=%d&listingcount=20&key=d2fdfec82a";

        /**
         * @param notUsed
         * @return
         */
        @Override
        protected String doInBackground(String... notUsed) {
            String url = String.format(YPSearchURL, getLocationString(), currentSearchTerm, currentRadius);
            Log.v(Constants.LOG_TAG, "URL: " + url);
            String result = getStream(url);
            return result;
        }

        /**
         * onPostExecute converts the JSON results into a MyObject
         *
         * @param result - should be the JSON data
         */
        @Override
        protected void onPostExecute(String result) {
            Log.v(Constants.LOG_TAG, result);

            // covert JSON string to a POJO
            MyObject myObj = jsonToMyObject(result);
            if (myObj != null) {
                listings = myObj.getSearchResult().getSearchListings().getSearchListing();
                pool.finishedTask();
            } else {
                Log.v(Constants.LOG_TAG, "No shops returned!!!");
            }
        }

        /**
         * converts a string of JSON data into a MyObject
         *
         * @param result
         * @return
         */
        private MyObject jsonToMyObject(String result) {
            MyObject myObject = null;
            if (result != null && result.length() > 0) {
                try {
                    Gson gson = new Gson();
                    myObject = gson.fromJson(result, MyObject.class);
                } catch (Exception ex) {
                    // just eat the exception
                }
            }
            return myObject;
        }

        /**
         * @param url
         * @return
         */
        private String getStream(String url) {
            String response = "";

            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            try {
                HttpResponse execute = client.execute(httpGet);
                InputStream content = execute.getEntity().getContent();

                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                String s = "";
                while ((s = buffer.readLine()) != null) {
                    response += s;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

    }

    public List getList() {
        return listings;
    }

    public boolean hasListings() {
        return listings != null;
    }

    public ObservablePool getPool() {
        return pool;
    }

    public LatLng getLocation() {
        return mLatLng;
    }

    public String getLocationString() {
        String loc = String.format("%5.6f:%5.6f", mLatLng.latitude, mLatLng.longitude);
        return loc;
    }

    public Map<String, Object>  getListingItem(int listingId) {

        for (Object item : listings) {
            Map<String, Object> shop = (Map<String, Object>)item;
            int currentId = (int) Math.round(((Double)shop.get("listingId")));
            Log.v(Constants.LOG_TAG, String.format("currentId = %d", currentId));
            if (currentId == listingId) {
                return shop;
            }
        }
        return null;
    }
}



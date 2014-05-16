package com.tekadept.coffeefinder.app;


import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.tekadept.coffeefinder.Dtos.MyObject;
import com.tekadept.coffeefinder.Dtos.SearchListing;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Troy on 5/15/14.
 */
public class CoffeeFinderApplication extends Application  {

    public String currentLocation = "34.052234:-118.243685";
    public String currentZipCode = "90023";
    public String currentSearchTerm = "coffee";
    public int currentRadius = 5;
    private List listings;
    private ObservablePool pool;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(Constants.LOG_TAG, "In Application onCreate");

        pool = new ObservablePool();
        pool.addTask(new DownloadSearchResultsTask(), new ArrayList<String>());
        pool.executeTasks();
    }


    private class DownloadSearchResultsTask extends AsyncTask<String, Void, String> {
        final static String YPSearchURL = "http://pubapi.atti.com/search-api/search/devapi/search?searchloc=%s&term=%s&format=json&sort=distance&radius=%d&listingcount=20&key=d2fdfec82a";

        /**
         * @param notUsed
         * @return
         */
        @Override
        protected String doInBackground(String... notUsed) {
            String url = String.format(YPSearchURL, currentLocation, currentSearchTerm, currentRadius);
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
            listings = myObj.getSearchResult().getSearchListings().getSearchListing();

            pool.finishedTask();
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

}
package com.tekadept.coffeefinder.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 */
public class ListingsActivity extends ActionBarActivity implements Observer, ListView.OnItemClickListener {

    private Activity myActivity;
    private CoffeeFinderApplication myApp;
    private ListView listView;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listings);

        myActivity = this;
        myApp = (CoffeeFinderApplication)this.getApplication();

        setWidgets();

        myApp.getPool().addObserver(this);
        if(myApp.hasListings()){
            update(null, null);
        }
    }

    private void setWidgets() {
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_map:
                intent = new Intent(myActivity, MapActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
//                intent = new Intent(myActivity, SettingsActivity.class);
//                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        List listings = myApp.getList();

        ListingsAdapter adapter = new ListingsAdapter(this, R.layout.shop_item_row, listings);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ListingsAdapter.ShopHolder holder = (ListingsAdapter.ShopHolder)view.getTag();

        Intent intent = new Intent(myActivity, DetailsActivity.class);
        intent.putExtra(Constants.LISTING_ID, (int) holder.listingId);
        startActivity(intent);
    }
}

package com.tekadept.coffeefinder.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tekadept.coffeefinder.app.R;
import com.tekadept.coffeefinder.Dtos.SearchListing;

public class ListingsAdapter extends ArrayAdapter<SearchListing> {
    Context context;
    int layoutResourceId;
    SearchListing shops[] = null;

    public ListingsAdapter(Context context, int layoutResourceId, SearchListing[] shops){
        super(context, layoutResourceId, shops);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.shops = shops;
    }

    static class ShopHolder {
        TextView txtBusinessName;
        TextView txtStreet;
        TextView txtDistance;
        Number listingId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ShopHolder holder = null;
        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ShopHolder();
            holder.txtBusinessName = (TextView)row.findViewById(R.id.txtBusinessName);
            holder.txtStreet = (TextView)row.findViewById(R.id.txtStreet);
            holder.txtDistance = (TextView)row.findViewById(R.id.txtDistance);

            row.setTag(holder);
        } else {
            holder = (ShopHolder)row.getTag();
        }

        SearchListing shop = shops[position];
        holder.txtBusinessName.setText(shop.getBusinessName());
        holder.txtStreet.setText(shop.getStreet());
        holder.txtDistance.setText(shop.getDistance().toString());
        holder.listingId = shop.getListingId();
        return row;
    }
}

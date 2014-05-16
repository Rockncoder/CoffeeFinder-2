package com.tekadept.coffeefinder.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tekadept.coffeefinder.Dtos.SearchListing;

import java.util.List;
import java.util.Map;

public class ListingsAdapter extends ArrayAdapter<SearchListing> {
    Context context;
    int layoutResourceId;
    List shops = null;

    public ListingsAdapter(Context context, int layoutResourceId, List shops){
        super(context, layoutResourceId, shops);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.shops = shops;
    }

    static class ShopHolder {
        TextView txtBusinessName;
        TextView txtStreet;
        TextView txtDistance;
        long listingId;
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

        Map<String, Object> shop = (Map<String, Object>)shops.get(position);
        holder.txtBusinessName.setText(shop.get("businessName").toString());
        holder.txtStreet.setText(shop.get("street").toString());
        holder.txtDistance.setText(shop.get("distance").toString());
        holder.listingId =  Math.round(((Double)shop.get("listingId")));
        return row;
    }
}

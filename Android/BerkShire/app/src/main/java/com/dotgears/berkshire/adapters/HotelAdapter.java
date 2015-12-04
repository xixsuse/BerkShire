package com.dotgears.berkshire.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dotgears.berkshire.R;
import com.dotgears.berkshire.model.Hotel;
import com.dotgears.berkshire.util.ImageLoader;

import java.util.ArrayList;

/**
 * Created by My PC on 04/12/2015.
 */
public class HotelAdapter extends ArrayAdapter<Hotel> {
    Context context;
    public ImageLoader imageLoader;
    ArrayList<Hotel> item;

    @Override
    public Hotel getItem(int position) {
        return super.getItem(position);
    }

    public HotelAdapter(Context context, int resourceId,
                        ArrayList<Hotel> items) {
        super(context, resourceId, items);
        this.context = context;
        imageLoader = new ImageLoader(getContext().getApplicationContext());
        this.item = items;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        HotelsHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row_hotel, null);
            holder = new HotelsHolder();
            holder.thumbnail = (ImageView) convertView
                    .findViewById(R.id.thumbnail);
            holder.hotelName = (TextView) convertView
                    .findViewById(R.id.hotelName);
            holder.hotelAddress = (TextView) convertView
                    .findViewById(R.id.hotelAddress);
            holder.hotelPrice = (TextView) convertView
                    .findViewById(R.id.hotelPrice);
            convertView.setTag(holder);
        } else
            holder = (HotelsHolder) convertView.getTag();
            String url = item.get(position).getHotelThumnail();
            imageLoader.DisplayImage(url, holder.thumbnail);
            holder.hotelName.setText(item.get(position).getHotelName());
            holder.hotelAddress.setText(item.get(position).getHotelAddress());
            holder.hotelPrice.setText(item.get(position).getHotelPrice());
        return convertView;

    }

    class HotelsHolder {
        ImageView thumbnail;
        TextView hotelName, hotelAddress, hotelPrice;
    }
}
//        holder.hotelAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.get(position).getAndroid())));
//            }
//        });
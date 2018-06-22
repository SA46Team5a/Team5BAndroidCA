package com.example.anthony.androidca;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {

    private List<String> items;
    int resource;

    public MyAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        switch (this.resource = resource) {
        }
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resource, null);
        String isbn = items.get(position);
        if (isbn != null) {
            TextView e = (TextView) v.findViewById(R.id.textView5);
            e.setText(isbn);
            ImageView image = (ImageView) v.findViewById(R.id.imageView5);
            //image.setImageBitmap(BookModel.getPhoto(true, isbn));
        }
        return v;
    }
}
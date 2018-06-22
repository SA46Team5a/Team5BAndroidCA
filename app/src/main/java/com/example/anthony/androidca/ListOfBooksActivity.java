package com.example.anthony.androidca;

import android.app.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListOfBooksActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_books);
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        List<String> books = BookModel.list();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, books);
        MyAdapter adapter = new MyAdapter(this, R.layout.listbooks, books);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v,
                                   int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("ISBN", item );
        startActivity(intent);


    }
}


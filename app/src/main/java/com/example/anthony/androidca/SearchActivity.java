package com.example.anthony.androidca;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {

    EditText searchText;
    Button searchAllBtn;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = findViewById(R.id.search_txt);
        searchBtn = findViewById(R.id.btn_search);
        searchAllBtn=findViewById(R.id.btn_search_all);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchCriteria = getSearchText();
                //List<String> listOfISBN = searchBookByTitle(searchCriteria);
                Intent i  = new Intent(SearchActivity.this,ListOfBooksActivity.class);
                //i.putExtra("listOfISBNs",listOfISBN);
                startActivity(i);
            }
        });

        searchAllBtn.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                new AsyncTask<Void,Void,List<String>>(){
                    @Override
                    protected List<String> doInBackground(Void... voids) {
                        //ArrayList<String> allISBN = BookModel.listAllBooks();
                        List<String> s = new ArrayList<String>();
                        s.add("123456");
                        //return allISBN;
                        return s;
                    }
                    @Override
                    protected void onPostExecute(List<String> result) {
                        Intent i  = new Intent(SearchActivity.this,ListOfBooksActivity.class);
                        //i.putExtra("listOfBooks",allISBN);
                        startActivity(i);
                    }
                }.execute();
            }
        });
    }

    public String getSearchText(){
        return searchText.getText().toString();
    }
}

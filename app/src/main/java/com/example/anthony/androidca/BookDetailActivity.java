package com.example.anthony.androidca;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SimpleAdapter;

import com.example.anthony.androidca.R;

public class BookDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        final Intent intent1=getIntent();
        final String ISBN=intent1.getStringExtra("ISBN");

       // BookModel book=BookModel.getBook(ISBN);

       // show(book);
        new AsyncTask<Void, Void,BookModel>() {
            @Override
            protected BookModel doInBackground(Void... params) {
                return BookModel.getBook(ISBN);
            }
            @Override
            protected void onPostExecute(BookModel result) {
                show(result);
            }
        }.execute();

    }
    void show(BookModel book)
    {
        int[] ids={R.id.editText1,R.id.editText2,R.id.editText3,R.id.editText4,R.id.editText5,R.id.editText6,R.id.editText7,R.id.editText8};
        String[] keys={"ISBN","title", "authorName","categoryName","price","discountedPrice","stockLevel","synopsis"};
        for(int i=0;i<keys.length;i++)
        {
            EditText e=(EditText) findViewById(ids[i]);
            e.setText(book.get(keys[i]).toString());
        }
    }
}


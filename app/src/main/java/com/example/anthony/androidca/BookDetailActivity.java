package com.example.anthony.androidca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class BookDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent intent1=getIntent();
        String ISBN=intent1.getStringExtra("ISBN");
        BookModel book=BookModel.getBook(ISBN);

        show(book);

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

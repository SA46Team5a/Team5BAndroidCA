package com.example.anthony.androidca;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookModel extends HashMap<String,Object> {


    final static String baseURL = "http://172.17.249.19/BookStore/Endpoint/IBookService.svc/";

    public BookModel(Object ISBN,Object title, Object authorName, Object categoryName, Object price,Object discountedPrice,Object stockLevel,Object synopsis) {

        put("ISBN", ISBN);
        put("title", title);
        put("authorName", authorName);
        put("categoryName", categoryName);
        put("price",price.toString());
        put("discountedPrice",discountedPrice.toString());
        put("stockLevel",stockLevel.toString());
        put("synopsis",synopsis);
    }




    public static List<String> list() {
        List<String> list = new ArrayList<String>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL + "Books");
        try {
            for (int i =0; i<a.length(); i++)
                list.add(a.getString(i));
        } catch (Exception e) {
            Log.e("BookModel.list()", "JSONArray error");
        }
        return(list);
    }

    public static BookModel getBook(String ISBN) {
        JSONObject b = JSONParser.getJSONFromUrl(baseURL  + "Books/" + ISBN );
        try {
            return new BookModel(b.get("ISBN"),b.get("title"),b.get("authorName"),b.get("categoryName"),b.get("price"),b.get("discountedPrice"),b.get("stockLevel"),b.get("synopsis"));
        } catch (Exception e) {
            Log.e("BookModel.getBook()", "JSONArray error");
        }
        return(null);
    }

    public static List<String> searchBookByTitle(String searchCriteria){
        List<String> allBooksISBN = list();
        List<String> searchResult = new ArrayList<String>();
        for (String isbn: allBooksISBN) {
            BookModel book = getBook(isbn);
            if((book.get("title").toString().toLowerCase()).contains(searchCriteria.toLowerCase())){
                searchResult.add(book.get("ISBN").toString());
            }
        }
        return searchResult;
    }
}

